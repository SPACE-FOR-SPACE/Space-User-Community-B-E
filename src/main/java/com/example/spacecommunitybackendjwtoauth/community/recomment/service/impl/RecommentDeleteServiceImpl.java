package com.example.spacecommunitybackendjwtoauth.community.recomment.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.exception.NotPermissionException;
import com.example.spacecommunitybackendjwtoauth.community.recomment.domain.RecommentEntity;
import com.example.spacecommunitybackendjwtoauth.community.recomment.exception.RecommentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.repository.RecommentRepository;
import com.example.spacecommunitybackendjwtoauth.community.recomment.service.RecommentDeleteService;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommentDeleteServiceImpl implements RecommentDeleteService {

    private final RecommentRepository recommentRepository;
    private final JWTUtil jwtUtil;

    @Override
    public void deleteRecomment(HttpServletRequest request, Long recommentId) {
        RecommentEntity recomment = recommentRepository.findById(recommentId).orElseThrow(RecommentNotExistException::new);
        if(!Objects.equals(recomment.getAuthor().getId(), getUserId(request))) throw new NotPermissionException();
        recommentRepository.delete(recomment);
    }
    private Long getUserId(HttpServletRequest request) {
        return jwtUtil.getUserId(jwtUtil.getAccessTokenFromHeaders(request).replaceFirst("Bearer ", ""));
    }
}
