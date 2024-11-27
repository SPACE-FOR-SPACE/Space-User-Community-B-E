package com.example.spacecommunitybackendjwtoauth.auth.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "JWTUserRequest", timeToLive = 1209600)
@Builder
@Getter
// RefreshToken Entity
public class JWTUserEntity {

    private Long userId;

    private String email;

    @Id
    private String refreshToken;

    private String role;
}