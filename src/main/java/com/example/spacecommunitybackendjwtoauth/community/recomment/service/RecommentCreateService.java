package com.example.spacecommunitybackendjwtoauth.community.recomment.service;

import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.dto.RecommentCreateDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface RecommentCreateService {
    void createRecomment(HttpServletRequest request, RecommentCreateDTO recommentCreateDTO);
}
