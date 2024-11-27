package com.example.spacecommunitybackendjwtoauth.user.service;

import com.example.spacecommunitybackendjwtoauth.user.presentation.dto.UserProfile;

public interface UserProfileService {
    UserProfile getUserProfile(String username);
}
