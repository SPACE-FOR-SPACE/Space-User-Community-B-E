package com.example.spacecommunitybackendjwtoauth.auth.service;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.UserJoinDTO;

public interface UserJoinMailService {
    void joinProcess(UserJoinDTO userJoinDTO);
}
