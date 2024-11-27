package com.example.spacecommunitybackendjwtoauth.community.comment.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.comment.exception.CommentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.dto.CommentUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.repository.CommentRepository;
import com.example.spacecommunitybackendjwtoauth.community.comment.service.CommentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentUpdateServiceImpl implements CommentUpdateService {

    private final CommentRepository commentRepository;

    @Override
    public void updateComment(CommentUpdateDTO commentUpdateDTO) {
        commentVerify(commentUpdateDTO.id());
        commentRepository.updateComment(commentUpdateDTO.id(), commentUpdateDTO.content());
    }

    private void commentVerify(Long commentId) {
        if(!commentRepository.existsById(commentId)) throw new CommentNotExistException();
    }
}
