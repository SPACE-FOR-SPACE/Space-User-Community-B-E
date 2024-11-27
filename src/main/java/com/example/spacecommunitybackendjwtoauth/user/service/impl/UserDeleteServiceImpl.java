package com.example.spacecommunitybackendjwtoauth.user.service.impl;

import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotExistException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import com.example.spacecommunitybackendjwtoauth.user.service.UserDeleteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDeleteServiceImpl implements UserDeleteService {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    @Override
    public void deleteUser(HttpServletRequest request) {
        Long userId = extractID(request);
        if(!userRepository.existsById(userId)) throw new UserNotExistException();
        userRepository.deleteById(userId);
    }
    private Long extractID(HttpServletRequest request) {
        return jwtUtil.getUserId(jwtUtil.getAccessTokenFromHeaders(request).replaceFirst("Bearer ", ""));
    }
}
