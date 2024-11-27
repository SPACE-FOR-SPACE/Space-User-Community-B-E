package com.example.spacecommunitybackendjwtoauth.community.comment.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.comment.domain.CommentEntity;
import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.dto.CommentReadDTO;
import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.repository.CommentRepository;
import com.example.spacecommunitybackendjwtoauth.community.comment.service.CommentReadService;
import com.example.spacecommunitybackendjwtoauth.community.doc.exception.DocumentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.repository.DocumentRepository;
import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.repository.RecommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentReadServiceImpl implements CommentReadService {

    private final DocumentRepository documentRepository;
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;

    @Override
    public List<CommentReadDTO> getAllComments(Long docId) {
        documentVerify(docId);
        List<CommentEntity> commentEntityList = commentRepository.findAllComments(docId);
        return commentEntityList.stream().map(c -> new CommentReadDTO(
                c.getId(),
                c.getAuthor().getUsername(),
                c.getContent(),
                recommentRepository.countAllRecomments(c.getId()),
                c.getCreatedAt().toString()
        )).toList();
    }

    private void documentVerify(Long docId) {
        if(!documentRepository.existsById(docId)) throw new DocumentNotExistException();
    }
}
