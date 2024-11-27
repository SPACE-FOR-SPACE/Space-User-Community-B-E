package com.example.spacecommunitybackendjwtoauth.user.service.impl;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.repository.RefreshTokenRepository;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotExistException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import com.example.spacecommunitybackendjwtoauth.user.presentation.dto.UserProfile;
import com.example.spacecommunitybackendjwtoauth.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Override
    public UserProfile getUserProfile(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(UserNotExistException::new);
        refreshTokenRepository.deleteByEmail(user.getEmail());
        return new UserProfile(user.getProfile(), user.getIntroduce(), user.getCreatedAt().toString());
    }
}
