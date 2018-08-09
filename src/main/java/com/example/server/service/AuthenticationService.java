package com.example.server.service;

import com.example.server.dto.AuthUserDto;
import com.example.server.dto.JsonException;
import com.example.server.dto.LoginRequestDto;
import com.example.server.dto.LoginResponseDto;
import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.security.SecurityHelper;
import com.example.server.security.model.JwtUserDetails;
import com.example.server.security.service.AuthenticationHelper;
import com.example.server.transformer.AuthUserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthUserTransformer authUserTransformer;
    @Autowired
    private AuthenticationHelper authenticationHelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {
        try {
            final String username = Optional.ofNullable(loginRequestDto.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Username should be passed."));

            final String password = Optional.ofNullable(loginRequestDto.getPassword())
                    .orElseThrow(() -> new BadCredentialsException("Password should be passed."));

            final UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
                    password);

            // Try to authenticate with this token
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);

            // Set generated JWT token to response header
            if (authResult.isAuthenticated()) {
                final JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();

                if (! userRepository.existsById(userDetails.getId())) {
                    throw new JsonException("User not exist in system.");
                }
                final User user = userRepository.getOne(userDetails.getId());

                final String token = this.authenticationHelper.generateToken(userDetails.getId());

                return new LoginResponseDto(token);
            } else {
                throw new JsonException("Authentication failed.");
            }

        } catch (final BadCredentialsException exception) {
            throw new JsonException("Username or password was incorrect. Please try again.", exception);
        }
    }

    /**
     * Get user info.
     * @return user info.
     */
    @Transactional(readOnly = true)
    public AuthUserDto getMe() {
        final Authentication authentication = SecurityHelper.getAuthenticationWithCheck();
        final User byUsername = userRepository.findByUsername(authentication.getName());

        return authUserTransformer.makeDto(byUsername);
    }
}
