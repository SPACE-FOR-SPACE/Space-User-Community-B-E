package com.example.spacecommunitybackendjwtoauth.community.like.service;

import com.example.spacecommunitybackendjwtoauth.community.like.presentation.dto.LikeDTO;

public interface LikeService {
    void likeDocument(LikeDTO likeDTO);
    void unlikeDocument(LikeDTO likeDTO);
}
