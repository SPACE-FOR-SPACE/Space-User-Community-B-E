package com.example.spacecommunitybackendjwtoauth.user.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserImageService {
    String getUserProfileImageUrl(MultipartFile profile) throws IOException;
}
