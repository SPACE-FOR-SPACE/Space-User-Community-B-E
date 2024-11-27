package com.example.spacecommunitybackendjwtoauth.admin.document.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.document.service.DocumentDeleteAdminService;
import com.example.spacecommunitybackendjwtoauth.community.doc.exception.DocumentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentDeleteAdminServiceImpl implements DocumentDeleteAdminService {

    private final DocumentRepository documentRepository;

    @Override
    public void deleteDoc(Long docId) {
        if(!documentRepository.existsById(docId)) throw new DocumentNotExistException();
        documentRepository.deleteById(docId);
    }
}
