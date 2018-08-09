package com.example.server.security;

import com.example.server.dto.JsonException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
public class SecurityHelper {

    public static Authentication getAuthenticationWithCheck() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final boolean checkAuthenticationExists = authentication != null && authentication.isAuthenticated();
        if (checkAuthenticationExists) {
            return authentication;
        }

        throw new JsonException("Authentication failed.");
    }
}
