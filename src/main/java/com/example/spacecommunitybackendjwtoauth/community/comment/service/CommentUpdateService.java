package com.example.spacecommunitybackendjwtoauth.community.comment.service;

import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.dto.CommentUpdateDTO;

public interface CommentUpdateService {
    void updateComment(CommentUpdateDTO comment);
}
