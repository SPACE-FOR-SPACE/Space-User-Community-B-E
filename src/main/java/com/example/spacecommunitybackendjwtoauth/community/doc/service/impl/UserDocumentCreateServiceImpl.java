package com.example.spacecommunitybackendjwtoauth.community.doc.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.doc.domain.DocumentEntity;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.dto.DocumentCreateDTO;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.repository.DocumentRepository;
import com.example.spacecommunitybackendjwtoauth.community.doc.service.UserDocumentCreateService;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotExistException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDocumentCreateServiceImpl implements UserDocumentCreateService {

    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;

    @Override
    public void createDocument(DocumentCreateDTO documentCreateDTO) {
        UserEntity user = userRepository.findById(documentCreateDTO.authorId()).orElseThrow(UserNotExistException::new);
        DocumentEntity document = DocumentEntity.builder()
                .author(user)
                .title(documentCreateDTO.title())
                .content(documentCreateDTO.content())
                .category(documentCreateDTO.category())
                .icon(documentCreateDTO.icon())
                .build();
        documentRepository.save(document);
    }
}
