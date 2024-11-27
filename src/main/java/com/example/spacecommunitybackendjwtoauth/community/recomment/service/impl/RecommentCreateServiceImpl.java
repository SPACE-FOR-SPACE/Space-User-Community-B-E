package com.example.spacecommunitybackendjwtoauth.community.recomment.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.comment.domain.CommentEntity;
import com.example.spacecommunitybackendjwtoauth.community.comment.exception.CommentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.repository.CommentRepository;
import com.example.spacecommunitybackendjwtoauth.community.recomment.domain.RecommentEntity;
import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.dto.RecommentCreateDTO;
import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.repository.RecommentRepository;
import com.example.spacecommunitybackendjwtoauth.community.recomment.service.RecommentCreateService;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotExistException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommentCreateServiceImpl implements RecommentCreateService {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;

    @Override
    public void createRecomment(HttpServletRequest request, RecommentCreateDTO recommentCreateDTO) {
        UserEntity user = getUserFromJWT(request);
        CommentEntity comment = commentRepository.findById(recommentCreateDTO.commentId()).orElseThrow(CommentNotExistException::new);
        RecommentEntity recomment = RecommentEntity.builder()
                .author(user)
                .comment(comment)
                .content(recommentCreateDTO.content())
                .build();
        recommentRepository.save(recomment);
    }

    private UserEntity getUserFromJWT(HttpServletRequest request) {
        String accessToken = jwtUtil.getAccessTokenFromHeaders(request).replaceFirst("Bearer ", "");
        Long userId = jwtUtil.getUserId(accessToken);
        return userRepository.findById(userId).orElseThrow(UserNotExistException::new);
    }
}
