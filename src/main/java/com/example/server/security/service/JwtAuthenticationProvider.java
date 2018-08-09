package com.example.server.security.service;

import com.example.server.model.User;
import com.example.server.repository.UserRepository;
import com.example.server.security.exception.ExpiredTokenAuthenticationException;
import com.example.server.security.exception.InvalidTokenAuthenticationException;
import com.example.server.security.model.JwtAuthenticationToken;
import com.example.server.security.model.JwtUserDetails;
import com.example.server.security.model.TokenPayload;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
@Component
@RequiredArgsConstructor
@Transactional
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final long MILLIS_IN_SECOND = 1000L;

    private final UserRepository userRepository;
    private final AuthenticationHelper authenticationHelper;

    @Override
    public Authentication authenticate(final Authentication authRequest) {
        // Getting string token from authentication request object
        final String token = StringUtils.trimToNull((String) authRequest.getCredentials());

        //  Deserialize token
        final TokenPayload tokenPayload = authenticationHelper.decodeToken(token);

        // Checking if token already expired and throwing an AuthenticationException in this case
        checkIsExpired(tokenPayload.getExp());

        // Getting user id from token
        final Long userEntityId = tokenPayload.getUserId();
        if (Objects.isNull(userEntityId)) {
            throw new InvalidTokenAuthenticationException("Token does not contain a user id.");
        }

        final User user = userRepository.getOne(userEntityId);
        if (Objects.isNull(user)) {
            throw new InvalidTokenAuthenticationException("Token does not contain existed user id.");
        }

        // Return authenticated Authentication
        final JwtUserDetails userDetails = new JwtUserDetails(user);
        return new JwtAuthenticationToken(userDetails);
    }

    private void checkIsExpired(final Long tokenExpirationTime) {
        if ((System.currentTimeMillis() / MILLIS_IN_SECOND) > tokenExpirationTime) {
            throw new ExpiredTokenAuthenticationException();
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}