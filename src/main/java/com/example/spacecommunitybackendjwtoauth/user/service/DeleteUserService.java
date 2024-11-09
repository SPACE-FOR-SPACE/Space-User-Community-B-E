package com.example.spacecommunitybackendjwtoauth.user.service;

import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import com.example.spacecommunitybackendjwtoauth.user.util.AccessTokenVerify;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@RequiredArgsConstructor
public class DeleteUserService {
    private final UserRepository userRepository;
    private final AccessTokenVerify accessTokenVerify;

    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(HttpServletRequest request) {
        Long userId = accessTokenVerify.userVerify(request);
        userRepository.deleteById(userId);
    }
}
