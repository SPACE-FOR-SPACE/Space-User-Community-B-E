package com.example.spacecommunitybackendjwtoauth.community.recomment.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.comment.domain.CommentEntity;
import com.example.spacecommunitybackendjwtoauth.community.comment.exception.CommentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.repository.CommentRepository;
import com.example.spacecommunitybackendjwtoauth.community.recomment.domain.RecommentEntity;
import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.dto.RecommentReadDTO;
import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.repository.RecommentRepository;
import com.example.spacecommunitybackendjwtoauth.community.recomment.service.RecommentReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommentReadServiceImpl implements RecommentReadService {

    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;

    @Override
    public List<RecommentReadDTO> getAllRecomments(Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        List<RecommentEntity> recomments = recommentRepository.findAllComments(comment.getId());
        return recomments.stream().map(r -> new RecommentReadDTO(r.getId(), r.getAuthor().getUsername(), r.getContent(), r.getCreatedAt().toString())).toList();
    }
}
