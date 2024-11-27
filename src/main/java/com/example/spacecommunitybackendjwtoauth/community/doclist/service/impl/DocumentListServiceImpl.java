package com.example.spacecommunitybackendjwtoauth.community.doclist.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.repository.DocumentRepository;
import com.example.spacecommunitybackendjwtoauth.community.doclist.exception.NotFoundOrderByException;
import com.example.spacecommunitybackendjwtoauth.community.doclist.presentation.dto.DocumentListElementDTO;
import com.example.spacecommunitybackendjwtoauth.community.doclist.service.DocumentListService;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotExistException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DocumentListServiceImpl implements DocumentListService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    @Override
    public List<DocumentListElementDTO> generalDocumentList(String orderBy) {
        if(!Objects.equals(orderBy, "createdAt") && !Objects.equals(orderBy, "likes")) throw new NotFoundOrderByException();
        Sort sort = Sort.by(Sort.Direction.DESC, orderBy);
        return documentRepository.findAll(sort).stream().map(e -> new DocumentListElementDTO(e.getId(), e.getTitle(), e.getAuthor().getUsername(), e.getCategory(), e.getIcon(), e.getCreatedAt().toString())).toList();
    }

    @Override
    public List<DocumentListElementDTO> userDoucmentList(String username, String orderBy) {
        if(!Objects.equals(orderBy, "createdAt") && !Objects.equals(orderBy, "likes")) throw new NotFoundOrderByException();
        if(!userRepository.existsByUsername(username)) throw new UserNotExistException();
        Sort sort = Sort.by(Sort.Direction.DESC, orderBy);
        return documentRepository.findAllByAuthorUsername(sort, username).stream().map(e -> new DocumentListElementDTO(e.getId(), e.getTitle(), e.getAuthor().getUsername(), e.getCategory(), e.getIcon(), e.getCreatedAt().toString())).toList();
    }
}
