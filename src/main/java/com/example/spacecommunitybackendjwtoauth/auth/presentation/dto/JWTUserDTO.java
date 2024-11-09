package com.example.spacecommunitybackendjwtoauth.auth.presentation.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "JWTUserRequest", timeToLive = 1209600)
@Builder
@Getter
// RefreshToken Entity
public class JWTUserDTO {

    private Long userId;

    private String email;

    @Id
    private String refreshToken;

    private String role;
}