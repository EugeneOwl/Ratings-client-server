package com.example.server.security.service;

import com.example.server.dto.JsonException;
import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.security.model.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User byUsername = this.userRepository.findByUsername(username);

        return Optional.ofNullable(byUsername)
                .map(JwtUserDetails::new)
                .orElseThrow(() -> new JsonException("User nor found."));
    }
}
