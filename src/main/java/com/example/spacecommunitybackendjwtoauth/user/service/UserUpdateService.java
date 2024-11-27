package com.example.spacecommunitybackendjwtoauth.user.service;

import com.example.spacecommunitybackendjwtoauth.user.presentation.dto.UserUpdateDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserUpdateService {
    void updateUser(HttpServletRequest request, HttpServletResponse response, UserUpdateDTO userUpdateDTO, MultipartFile profile) throws IOException;
}
