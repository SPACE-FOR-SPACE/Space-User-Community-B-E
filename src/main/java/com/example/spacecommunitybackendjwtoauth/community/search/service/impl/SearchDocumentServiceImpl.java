package com.example.spacecommunitybackendjwtoauth.community.search.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.doc.domain.DocumentEntity;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.repository.DocumentRepository;
import com.example.spacecommunitybackendjwtoauth.community.search.presentation.dto.DocumentDTO;
import com.example.spacecommunitybackendjwtoauth.community.search.service.SearchDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchDocumentServiceImpl implements SearchDocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public List<DocumentDTO> searchDocuments(String query) {
        List<DocumentEntity> documentEntities = documentRepository.findAllByTitleContainingOrderByCreatedAtDesc(query);
        return documentEntities.stream().map(d -> new DocumentDTO(d.getId(), d.getTitle(), d.getAuthor().getUsername(), d.getCategory(), d.getIcon(), d.getCreatedAt().toString())).toList();
    }

}
