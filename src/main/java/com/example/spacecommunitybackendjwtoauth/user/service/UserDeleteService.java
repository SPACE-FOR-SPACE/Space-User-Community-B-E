package com.example.spacecommunitybackendjwtoauth.user.service;

import jakarta.servlet.http.HttpServletRequest;

public interface UserDeleteService {
    void deleteUser(HttpServletRequest request);
}
