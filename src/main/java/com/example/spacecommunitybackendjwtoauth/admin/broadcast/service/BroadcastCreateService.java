package com.example.spacecommunitybackendjwtoauth.admin.broadcast.service;

import jakarta.servlet.http.HttpServletRequest;

public interface BroadcastCreateService {
    void createBroadcast(String title, String content, HttpServletRequest httpServletRequest);
}
