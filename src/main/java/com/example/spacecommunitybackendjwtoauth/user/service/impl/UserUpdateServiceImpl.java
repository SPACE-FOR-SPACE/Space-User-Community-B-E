package com.example.spacecommunitybackendjwtoauth.user.service.impl;

import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.exception.IncorrectPassword;
import com.example.spacecommunitybackendjwtoauth.user.exception.NicknameExistedException;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotExistException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.dto.UserUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import com.example.spacecommunitybackendjwtoauth.user.service.UserImageService;
import com.example.spacecommunitybackendjwtoauth.user.service.UserUpdateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class UserUpdateServiceImpl implements UserUpdateService {
    private final UserRepository userRepository;
    private final UserImageService imageService;
    private final JWTUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void updateUser(HttpServletRequest request, HttpServletResponse response, UserUpdateDTO userUpdateDTO, MultipartFile profile) throws IOException{
        System.out.println(request.getContentType());
        Long userId = getUserID(request);
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotExistException::new);

        String username = updateUsername(response, user, userUpdateDTO);
        String encodedPassword = updateEncodedPassword(user, userUpdateDTO);
        String profileURL = updateProfile(profile);
        String introduce = updateIntroduce(userUpdateDTO);

        userRepository.updateUser(userId, username, encodedPassword, profileURL, introduce);

    }

    private Long getUserID(HttpServletRequest request) {
        return jwtUtil.getUserId(jwtUtil.getAccessTokenFromHeaders(request).replaceFirst("Bearer ", ""));
    }

    private String updateUsername(HttpServletResponse response, UserEntity user, UserUpdateDTO userUpdateDTO) {
        String username = null;
        if(userUpdateDTO.username().isPresent()) {
            String beforeUsername = user.getUsername();
            String presentUsername = userUpdateDTO.username().get();
            if(!presentUsername.equals(beforeUsername) && userRepository.existsByUsername(presentUsername)) throw new NicknameExistedException();
            String accessToken = jwtUtil.createAccessToken(user.getId(), presentUsername, user.getRole());
            String refreshToken = jwtUtil.createRefreshToken(user.getId(), presentUsername, user.getRole());
            response.setContentType("application/json");
            response.setHeader("Authorization", "Bearer " + accessToken);
            response.addHeader(HttpHeaders.SET_COOKIE, refreshToken);
            username = presentUsername;
        }
        return username;
    }

    private String updateEncodedPassword(UserEntity user, UserUpdateDTO userUpdateDTO) {
        String encodedPassword = null;
        if(userUpdateDTO.beforePassword().isPresent() && userUpdateDTO.afterPassword().isPresent()) {
            String beforePassword = user.getPassword();
            if(!passwordEncoder.matches(userUpdateDTO.beforePassword().get(), beforePassword)) throw new IncorrectPassword();
            encodedPassword = passwordEncoder.encode(userUpdateDTO.afterPassword().get());
        }
        return encodedPassword;
    }

    private String updateProfile(MultipartFile profile) throws IOException {
        String profileURL = null;
        if(profile!=null && profile.getOriginalFilename().matches(".*\\.(jpg|png|jpeg)$")) {
            profileURL = imageService.getUserProfileImageUrl(profile);
        }
        return profileURL;
    }

    private String updateIntroduce(UserUpdateDTO userUpdateDTO) {
        String introduce = null;
        if(userUpdateDTO.introduce().isPresent()) {
            introduce = userUpdateDTO.introduce().get();
        }
        return introduce;
    }
}
