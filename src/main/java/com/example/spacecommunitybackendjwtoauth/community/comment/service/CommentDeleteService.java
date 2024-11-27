package com.example.spacecommunitybackendjwtoauth.community.comment.service;

import jakarta.servlet.http.HttpServletRequest;

public interface CommentDeleteService {
    void commentDelete(HttpServletRequest request, Long id);
}
