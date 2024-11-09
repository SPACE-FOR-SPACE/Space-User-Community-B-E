package com.example.spacecommunitybackendjwtoauth.auth.service.implementation;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.JoinUserDTO;
import com.example.spacecommunitybackendjwtoauth.user.Role;
import com.example.spacecommunitybackendjwtoauth.user.User;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserExistedException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 회원가입 서비스
@Slf4j
@Service
@RequiredArgsConstructor
public class UserJoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void joinProcess(JoinUserDTO joinUserRequest) {

        log.warn("회원가입 서비스 로그");

        String username = joinUserRequest.username();
        String email = joinUserRequest.email();
        String encodedPassword = passwordEncoder.encode(joinUserRequest.password());
        Integer age = Integer.valueOf(joinUserRequest.age());
        Role role = Role.USER;

        Boolean isExsits = userRepository.existsByEmail(email);

        if(isExsits) {
            throw new UserExistedException();
        }

        User user = User.UserRegisterBuilder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .role(role)
                .age(age)
                .build();

        userRepository.save(user);
    }
}
