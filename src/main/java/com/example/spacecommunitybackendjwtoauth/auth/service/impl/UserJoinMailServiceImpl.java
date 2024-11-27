package com.example.spacecommunitybackendjwtoauth.auth.service.impl;

import com.example.spacecommunitybackendjwtoauth.auth.domain.EmailAuthenticateEntity;
import com.example.spacecommunitybackendjwtoauth.auth.exception.EmailCodeNotCorrectException;
import com.example.spacecommunitybackendjwtoauth.auth.exception.JoinEmailNotExistException;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.UserJoinDTO;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.repository.EmailAuthenticateRepository;
import com.example.spacecommunitybackendjwtoauth.auth.service.UserJoinMailService;
import com.example.spacecommunitybackendjwtoauth.user.domain.Role;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserExistedException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

// 회원가입 서비스
@Service
@RequiredArgsConstructor
@Transactional
public class UserJoinMailServiceImpl implements UserJoinMailService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailAuthenticateRepository emailAuthenticateRepository;

    @Override
    public void joinProcess(UserJoinDTO joinUserRequest) {

        EmailAuthenticateEntity emailAuthentication = emailAuthenticateRepository.findById(joinUserRequest.email()).orElseThrow(JoinEmailNotExistException::new);
        if(!Objects.equals(joinUserRequest.token(), emailAuthentication.getToken())) throw new EmailCodeNotCorrectException();
        emailAuthenticateRepository.delete(emailAuthentication);

        String username = joinUserRequest.username();
        String email = joinUserRequest.email();
        String encodedPassword = passwordEncoder.encode(joinUserRequest.password());
        Long age = joinUserRequest.age();

        Boolean isExsits1 = userRepository.existsByEmail(email);
        Boolean isExsits2 = userRepository.existsByUsername(username);

        if(isExsits1 || isExsits2) {
            throw new UserExistedException();
        }

        UserEntity user = UserEntity.UserRegisterBuilder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .role(Role.USER)
                .age(age)
                .build();
        userRepository.save(user);
    }
}
