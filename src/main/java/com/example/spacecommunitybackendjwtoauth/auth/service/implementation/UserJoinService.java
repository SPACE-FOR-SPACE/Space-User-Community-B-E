package com.example.spacecommunitybackendjwtoauth.auth.service.implementation;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.JoinUserRequest;
import com.example.spacecommunitybackendjwtoauth.user.Role;
import com.example.spacecommunitybackendjwtoauth.user.User;
import com.example.spacecommunitybackendjwtoauth.user.exceptions.UserExistedException;
import com.example.spacecommunitybackendjwtoauth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserJoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void joinProcess(JoinUserRequest joinUserRequest) {

        String username = joinUserRequest.username();
        String email = joinUserRequest.email();
        String encodedPassword = passwordEncoder.encode(joinUserRequest.password());
        Integer age = Integer.valueOf(joinUserRequest.age());
        Role role = Role.USER;

        Boolean isExsits = userRepository.existsUserByEmail(email);

        if(isExsits) {
            throw new UserExistedException();
        }

        User user = User.JWTRegisterBuilder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .role(role)
                .age(age)
                .build();

        userRepository.save(user);
    }
}
