package com.example.server.security.model;

import com.example.server.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
@Getter
public class JwtUserDetails implements UserDetails {

    final private Long id;
    final private String username;
    final private String password;
    final private Set<GrantedAuthority> authorities;

    public JwtUserDetails(final User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new HashSet<>();
        user.getRoles().stream()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getLabel())));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
