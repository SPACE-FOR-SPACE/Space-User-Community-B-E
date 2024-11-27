package com.example.spacecommunitybackendjwtoauth.community.recomment.service.impl;

import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.repository.CommentRepository;
import com.example.spacecommunitybackendjwtoauth.community.recomment.domain.RecommentEntity;
import com.example.spacecommunitybackendjwtoauth.community.recomment.exception.RecommentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.dto.RecommentUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.repository.RecommentRepository;
import com.example.spacecommunitybackendjwtoauth.community.recomment.service.RecommentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommentUpdateServiceImpl implements RecommentUpdateService {

    private final RecommentRepository recommentRepository;

    @Override
    public void updateRecomment(RecommentUpdateDTO recomment) {
        RecommentEntity recommentEntity = recommentRepository.findById(recomment.id()).orElseThrow(RecommentNotExistException::new);
        recommentRepository.updateRecomment(recomment.id(), recomment.content());
    }
}
