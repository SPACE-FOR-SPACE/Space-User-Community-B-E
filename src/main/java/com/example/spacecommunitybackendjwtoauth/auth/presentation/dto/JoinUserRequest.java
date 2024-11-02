package com.example.spacecommunitybackendjwtoauth.auth.presentation.dto;

public record JoinUserRequest(
        String email, String username, String password, String age
) {}