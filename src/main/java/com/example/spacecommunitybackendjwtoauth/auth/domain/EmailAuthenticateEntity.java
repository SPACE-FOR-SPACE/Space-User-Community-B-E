package com.example.spacecommunitybackendjwtoauth.auth.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "EmailAuthenticateEntity", timeToLive = 600)
@Builder
@Getter
public class EmailAuthenticateEntity {
    @Id
    private String email;

    private String token;
}
