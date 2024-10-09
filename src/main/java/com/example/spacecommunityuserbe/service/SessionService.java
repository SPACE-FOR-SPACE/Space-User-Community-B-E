package com.example.spacecommunityuserbe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveSession(String username, String sessionId) {
        redisTemplate.opsForValue().set(sessionId, username, 10, TimeUnit.SECONDS); // Redis TTL만료기간을 2시간으로 잡음.
    }

    public String getSession(String username) {
        return (String) redisTemplate.opsForValue().get(username);
    }

    public void deleteSession(String username) {
        redisTemplate.opsForValue().getAndDelete(username);
    }
}