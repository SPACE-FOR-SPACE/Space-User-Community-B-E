package com.example.spacecommunitybackendjwtoauth.community.comment.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.comment.domain.CommentEntity;
import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.dto.CommentCreateDTO;
import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.repository.CommentRepository;
import com.example.spacecommunitybackendjwtoauth.community.comment.service.CommentCreateService;
import com.example.spacecommunitybackendjwtoauth.community.doc.domain.DocumentEntity;
import com.example.spacecommunitybackendjwtoauth.community.doc.exception.DocumentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.repository.DocumentRepository;
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
public class CommentCreateServiceImpl implements CommentCreateService {
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final CommentRepository commentRepository;

    @Override
    public void createComment(HttpServletRequest request, CommentCreateDTO commentCreateDTO) {
        UserEntity author = getUserID(request);
        DocumentEntity document = documentRepository.findById(commentCreateDTO.documentId()).orElseThrow(DocumentNotExistException::new);
        CommentEntity comment = CommentEntity.builder()
                .author(author)
                .document(document)
                .content(commentCreateDTO.content())
                .build();
        commentRepository.save(comment);
    }

    private UserEntity getUserID(HttpServletRequest request) {
        String accessToken = jwtUtil.getAccessTokenFromHeaders(request).replaceFirst("Bearer ", "");
        return userRepository.findById(jwtUtil.getUserId(accessToken)).orElseThrow(UserNotExistException::new);
    }
}
