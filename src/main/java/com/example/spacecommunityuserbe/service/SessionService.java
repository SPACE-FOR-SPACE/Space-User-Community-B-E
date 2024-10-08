package com.example.spacecommunityuserbe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveSession(String username, String sessionId) {
        redisTemplate.opsForValue().set(username, sessionId);
    }

    public String getSession(String username) {
        return (String) redisTemplate.opsForValue().get(username);
    }

    public void deleteSession(String username) {
        redisTemplate.opsForValue().getAndDelete(username);
    }

}