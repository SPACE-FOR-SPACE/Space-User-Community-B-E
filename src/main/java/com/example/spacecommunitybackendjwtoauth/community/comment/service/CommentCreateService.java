package com.example.spacecommunitybackendjwtoauth.community.comment.service;

import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.dto.CommentCreateDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface CommentCreateService {
    void createComment(HttpServletRequest request, CommentCreateDTO comment);
}
