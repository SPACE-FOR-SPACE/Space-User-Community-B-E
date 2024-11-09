package com.example.spacecommunitybackendjwtoauth.user.service;

import com.example.spacecommunitybackendjwtoauth.user.exception.NicknameExistedException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.dto.UserUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import com.example.spacecommunitybackendjwtoauth.user.util.AccessTokenVerify;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class UpdateUserService {
    private final UserRepository userRepository;
    private final AccessTokenVerify accessTokenVerify;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ImageService imageService;

    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(HttpServletRequest httpServletRequest, UserUpdateDTO userUpdate, MultipartFile profile) throws IOException {
        boolean existsByUsername = userRepository.existsByUsername(userUpdate.username());
        if(existsByUsername) {
            throw new NicknameExistedException();
        }
        Long userId = accessTokenVerify.userVerify(httpServletRequest);
        String encodedPassword = passwordEncoder.encode(userUpdate.password());
        String profileURL = imageService.uploadImage(profile);
        userRepository.updateUserByEmail(userId, userUpdate.username(), encodedPassword, profileURL);
    }
}
