package com.example.spacecommunitybackendjwtoauth.auth.presentation.dto;

import com.example.spacecommunitybackendjwtoauth.user.Role;
import com.example.spacecommunitybackendjwtoauth.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// 회원 DTO
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;

    private GrantedAuthority getGrandAuthority(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.name());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (user.getRole()) {
            case GUEST:
                authorities.add(getGrandAuthority(Role.GUEST));
                break;
            case USER:
                authorities.add(getGrandAuthority(Role.USER));
                break;
            case ADMIN:
                authorities.add(getGrandAuthority(Role.ADMIN));
                break;
        }
        return authorities;
    }

    public Long getUserId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 블랙리스트
        return true;
    }

    public String getEmail() { return user.getEmail(); }

    public Role getRole() { return user.getRole(); }
}