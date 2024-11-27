package com.example.spacecommunitybackendjwtoauth.community.like.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.doc.domain.DocumentEntity;
import com.example.spacecommunitybackendjwtoauth.community.doc.exception.DocumentAlreadyLikeException;
import com.example.spacecommunitybackendjwtoauth.community.doc.exception.DocumentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.doc.exception.DocumentNotLikeException;
import com.example.spacecommunitybackendjwtoauth.community.like.presentation.dto.LikeDTO;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.repository.DocumentRepository;
import com.example.spacecommunitybackendjwtoauth.community.like.service.LikeService;
import com.example.spacecommunitybackendjwtoauth.community.like.domain.LikeEntity;
import com.example.spacecommunitybackendjwtoauth.community.like.presentation.repository.LikeRepository;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotExistException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeServiceImpl implements LikeService {

    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final LikeRepository likeRepository;

    @Override
    public void likeDocument(LikeDTO likeDTO) {
        UserEntity user = userRepository.findById(likeDTO.authorId()).orElseThrow(UserNotExistException::new);
        DocumentEntity document = documentRepository.findById(likeDTO.documentId()).orElseThrow(DocumentNotExistException::new);

        if(likeRepository.existsByDocumentAndUser(document, user)) throw new DocumentAlreadyLikeException();

        likeRepository.save(LikeEntity.builder()
                .document(document)
                .user(user)
                .build());
        documentRepository.likeDocument(likeDTO.documentId(), 1L);
    }

    @Override
    public void unlikeDocument(LikeDTO likeDTO) {
        UserEntity user = userRepository.findById(likeDTO.authorId()).orElseThrow(UserNotExistException::new);
        DocumentEntity document = documentRepository.findById(likeDTO.documentId()).orElseThrow(DocumentNotExistException::new);
        if(!likeRepository.existsByDocumentAndUser(document, user)) throw new DocumentNotLikeException();
        likeRepository.deleteByDocumentAndUser(document, user);
        documentRepository.likeDocument(likeDTO.documentId(), -1L);
    }
}
