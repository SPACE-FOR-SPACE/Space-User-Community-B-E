package com.example.spacecommunitybackendjwtoauth.jwt.filter;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.CustomUserDetails;
import com.example.spacecommunitybackendjwtoauth.auth.service.CustomUserDetailsService;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserLoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// 인증 로직 구현
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(email);

        if(!passwordEncoder.matches(password, customUserDetails.getPassword())) {
            throw new UserLoginException();
        }

        return new UsernamePasswordAuthenticationToken(customUserDetails, password, customUserDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
