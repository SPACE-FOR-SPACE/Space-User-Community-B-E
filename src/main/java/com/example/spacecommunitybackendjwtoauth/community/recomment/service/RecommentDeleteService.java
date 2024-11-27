package com.example.spacecommunitybackendjwtoauth.community.recomment.service;

import jakarta.servlet.http.HttpServletRequest;

public interface RecommentDeleteService {
    void deleteRecomment(HttpServletRequest request, Long recommentId);
}
