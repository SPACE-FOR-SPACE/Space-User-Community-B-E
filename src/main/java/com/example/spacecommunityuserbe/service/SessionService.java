package com.example.spacecommunityuserbe.service;

import com.example.spacecommunityuserbe.exception.CustomException;
import com.example.spacecommunityuserbe.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveSession(String username, String sessionId) {
        try {
            if(getSession(sessionId) != null) throw new CustomException(ErrorCode.SESSION_EXSITS);
            redisTemplate.opsForValue().set(sessionId, username, 2, TimeUnit.HOURS); // Redis TTL만료기간을 2시간으로 잡음.
        }
        catch (DataException e) {
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        }
    }

    public String getSession(String sessionId) {
        try {
            return (String) redisTemplate.opsForValue().get(sessionId);
        }
        catch (DataException e) {
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        }
    }

    public void deleteSession(String sessionId) {
        try {
            if(getSession(sessionId) == null) throw new CustomException(ErrorCode.SESSION_NOT_FOUND);
            redisTemplate.opsForValue().getAndDelete(sessionId);
        }
        catch (DataException e) {
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        }
    }
}