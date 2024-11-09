package com.example.spacecommunitybackendjwtoauth.user.service;

import com.example.spacecommunitybackendjwtoauth.user.User;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotFoundException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import com.example.spacecommunitybackendjwtoauth.user.presentation.vo.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileUserService {
    private final UserRepository userRepository;

    public ResponseEntity<?> profileUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotFoundException();
        }
        UserProfile userProfile = new UserProfile(user.get().getUsername(), user.get().getProfile());
        return ResponseEntity.ok().body(userProfile);
    }
}
