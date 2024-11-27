package com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.broadcast.domain.BroadcastEntity;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.repository.BroadcastRepository;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.BroadcastCreateService;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BroadcastCreateServiceImpl implements BroadcastCreateService {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final BroadcastRepository broadcastRepository;

    @Override
    public void createBroadcast(String title, String content, HttpServletRequest httpServletRequest) {
        Long userId = jwtUtil.getUserId(jwtUtil.getAccessTokenFromHeaders(httpServletRequest).replaceFirst("Bearer ",""));
        UserEntity user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        broadcastRepository.save(BroadcastEntity.builder().title(title).author(user).contents(content).build());
    }
}
