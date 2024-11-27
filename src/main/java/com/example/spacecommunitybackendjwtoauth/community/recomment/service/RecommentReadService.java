package com.example.spacecommunitybackendjwtoauth.community.recomment.service;

import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.dto.RecommentReadDTO;

import java.util.List;

public interface RecommentReadService {
    List<RecommentReadDTO> getAllRecomments(Long commentId);
}
