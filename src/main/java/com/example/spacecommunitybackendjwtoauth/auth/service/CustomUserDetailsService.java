package com.example.spacecommunitybackendjwtoauth.auth.service;

import com.example.spacecommunitybackendjwtoauth.user.User;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.CustomUserDetails;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 회원 EMAIL 찾는 서비스
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("이메일이 올바르지 않습니다." + email);
        }
        return new CustomUserDetails(user);
    }
}
