package com.example.spacecommunitybackendjwtoauth.community.doc.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.doc.domain.DocumentEntity;
import com.example.spacecommunitybackendjwtoauth.community.doc.exception.DocumentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.dto.DocumentReadDTO;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.repository.DocumentRepository;
import com.example.spacecommunitybackendjwtoauth.community.doc.service.UserDocumentReadService;
import com.example.spacecommunitybackendjwtoauth.community.like.presentation.repository.LikeRepository;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotExistException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDocumentReadServiceImpl implements UserDocumentReadService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final JWTUtil jwtUtil;

    @Override
    public DocumentReadDTO getDocumentById(HttpServletRequest request, Long documentId) {
        DocumentEntity document = documentRepository.findById(documentId).orElseThrow(DocumentNotExistException::new);
        boolean likeStatus = false;
        try {
            Long userId = jwtUtil.getUserId(jwtUtil.getAccessTokenFromHeaders(request).replaceFirst("Bearer ", ""));
            UserEntity user = userRepository.findById(userId).orElseThrow(UserNotExistException::new);
            likeStatus = likeRepository.existsByDocumentAndUser(document, user);
        }
        catch(MalformedJwtException je) {
            // no-op
        }
        return new DocumentReadDTO(
                documentId,
                document.getAuthor().getUsername(),
                document.getTitle(),
                document.getContent(),
                document.getIcon(),
                document.getLikes(),
                document.getCategory(),
                likeStatus,
                document.getCreatedAt().toString()
        );
    }
}
