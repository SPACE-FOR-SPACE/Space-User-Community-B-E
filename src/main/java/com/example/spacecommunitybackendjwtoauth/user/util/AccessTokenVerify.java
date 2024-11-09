package com.example.spacecommunitybackendjwtoauth.user.util;

import com.example.spacecommunitybackendjwtoauth.jwt.exception.InvalidTokenException;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotFoundException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessTokenVerify {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    public Long userVerify(HttpServletRequest request) throws InvalidTokenException, UserNotFoundException {
        String accessToken = jwtUtil.getAccessTokenFromHeaders(request);
        accessToken = accessToken.replaceFirst("Bearer ", "");
        if (!jwtUtil.getCategory(accessToken).equals("access")) throw new InvalidTokenException();
        Long userId = jwtUtil.getUserId(accessToken);
        boolean existsById = userRepository.existsById(userId);
        if (!existsById) throw new UserNotFoundException();
        return userId;
    }
}
