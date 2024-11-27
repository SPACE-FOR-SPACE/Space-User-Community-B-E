package com.example.spacecommunitybackendjwtoauth.community.doc.service;

import jakarta.servlet.http.HttpServletRequest;

public interface UserDocumentDeleteService {
    void deleteDocument(HttpServletRequest request, Long documentId);
}
