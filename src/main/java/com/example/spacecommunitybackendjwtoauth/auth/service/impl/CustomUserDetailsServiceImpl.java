package com.example.spacecommunitybackendjwtoauth.auth.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.repository.BanRepository;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.CustomUserDetails;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 회원 EMAIL 찾는 서비스
@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final BanRepository banRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("이메일이 올바르지 않습니다." + username));
        return new CustomUserDetails(user, banRepository);
    }
}
