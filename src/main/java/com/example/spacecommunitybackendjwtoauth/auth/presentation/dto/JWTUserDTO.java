package com.example.spacecommunitybackendjwtoauth.auth.presentation.dto;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;

@RedisHash(value = "JWTUserRequest", timeToLive = 1209600)
@Builder
@Getter
public class JWTUserDTO {

    @Id
    private Long id;

    private String email;

    private String refreshToken;

    private Date expiration;

    private String role;
}