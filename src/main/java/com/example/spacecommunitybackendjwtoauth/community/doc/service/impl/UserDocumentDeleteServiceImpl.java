package com.example.spacecommunitybackendjwtoauth.community.doc.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.doc.domain.DocumentEntity;
import com.example.spacecommunitybackendjwtoauth.community.doc.exception.DocumentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.exception.NotPermissionException;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.repository.DocumentRepository;
import com.example.spacecommunitybackendjwtoauth.community.doc.service.UserDocumentDeleteService;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDocumentDeleteServiceImpl implements UserDocumentDeleteService {

    private final DocumentRepository documentRepository;
    private final JWTUtil jwtUtil;

    @Override
    public void deleteDocument(HttpServletRequest request, Long documentId) {
        DocumentEntity document = documentRepository.findById(documentId).orElseThrow(DocumentNotExistException::new);
        if(!Objects.equals(getUserId(request), document.getAuthor().getId())) throw new NotPermissionException();
        documentRepository.delete(document);
    }

    private Long getUserId(HttpServletRequest request) {
        return jwtUtil.getUserId(jwtUtil.getAccessTokenFromHeaders(request).replaceFirst("Bearer ", ""));
    }
}