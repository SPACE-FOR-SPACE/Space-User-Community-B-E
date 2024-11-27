package com.example.spacecommunitybackendjwtoauth.community.doc.service;

import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.dto.DocumentReadDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface UserDocumentReadService {
    DocumentReadDTO getDocumentById(HttpServletRequest request, Long documentId);
}
