package com.beingapple.webservice.auth.jwt;

import com.beingapple.webservice.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private JwtUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("Bad token");
        }

        String token = authentication.getCredentials().toString();

        if (JwtUtil.verify(token)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(token);
            return new JwtAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
