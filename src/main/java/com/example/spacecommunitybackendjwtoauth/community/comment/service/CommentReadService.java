package com.example.spacecommunitybackendjwtoauth.community.comment.service;

import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.dto.CommentReadDTO;

import java.util.List;

public interface CommentReadService {
    List<CommentReadDTO> getAllComments(Long docId);
}
