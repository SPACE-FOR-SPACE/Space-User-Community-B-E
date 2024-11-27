package com.example.spacecommunitybackendjwtoauth.community.search.service;

import com.example.spacecommunitybackendjwtoauth.community.search.presentation.dto.DocumentDTO;

import java.util.List;

public interface SearchDocumentService {
    List<DocumentDTO> searchDocuments(String query);
}
