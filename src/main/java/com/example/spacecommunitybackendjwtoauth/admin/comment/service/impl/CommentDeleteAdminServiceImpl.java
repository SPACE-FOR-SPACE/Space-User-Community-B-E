package com.example.spacecommunitybackendjwtoauth.admin.comment.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.comment.service.CommentDeleteService;
import com.example.spacecommunitybackendjwtoauth.community.comment.exception.CommentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentDeleteAdminServiceImpl implements CommentDeleteService {

    private final CommentRepository commentRepository;

    @Override
    public void deleteComment(Long commentId) {
        if(!commentRepository.existsById(commentId)) throw new CommentNotExistException();
        commentRepository.deleteById(commentId);
    }
}
