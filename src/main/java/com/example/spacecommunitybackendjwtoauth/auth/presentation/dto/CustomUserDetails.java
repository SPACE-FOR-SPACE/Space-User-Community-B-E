package com.example.spacecommunitybackendjwtoauth.auth.presentation.dto;

import com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.repository.BanRepository;
import com.example.spacecommunitybackendjwtoauth.user.domain.Role;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
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
    private final UserEntity userEntity;
    private final BanRepository banRepository;

    private GrantedAuthority getGrandAuthority(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.name());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (userEntity.getRole()) {
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
        return userEntity.getId();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !banRepository.existsByUser(userEntity);
    }

    public Role getRole() { return userEntity.getRole(); }
}
