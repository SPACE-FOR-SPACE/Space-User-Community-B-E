package com.example.spacecommunitybackendjwtoauth.community.comment.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.comment.domain.CommentEntity;
import com.example.spacecommunitybackendjwtoauth.community.comment.exception.CommentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.repository.CommentRepository;
import com.example.spacecommunitybackendjwtoauth.community.comment.service.CommentDeleteService;
import com.example.spacecommunitybackendjwtoauth.community.exception.NotPermissionException;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentDeleteServiceImpl implements CommentDeleteService {
    private final CommentRepository commentRepository;
    private final JWTUtil jwtUtil;

    @Override
    public void commentDelete(HttpServletRequest request, Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        if(!Objects.equals(comment.getAuthor().getId(), getUserId(request))) throw new NotPermissionException();
        commentRepository.delete(comment);
    }

    private Long getUserId(HttpServletRequest request) {
        return jwtUtil.getUserId(jwtUtil.getAccessTokenFromHeaders(request).replaceFirst("Bearer ", ""));
    }
}
