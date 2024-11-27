package com.example.spacecommunitybackendjwtoauth.community.doc.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.doc.exception.DocumentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.dto.DocumentUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.repository.DocumentRepository;
import com.example.spacecommunitybackendjwtoauth.community.doc.service.UserDocumentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDocumentUpdateServiceImpl implements UserDocumentUpdateService {

    private final DocumentRepository documentRepository;

    @Override
    public void updateDocument(DocumentUpdateDTO documentUpdateDTO) {
        if(!documentRepository.existsById(documentUpdateDTO.documentId())) throw new DocumentNotExistException();
        documentRepository.updateById(
                documentUpdateDTO.documentId(),
                documentUpdateDTO.title(),
                documentUpdateDTO.content(),
                documentUpdateDTO.category(),
                documentUpdateDTO.icon()
        );
    }
}
