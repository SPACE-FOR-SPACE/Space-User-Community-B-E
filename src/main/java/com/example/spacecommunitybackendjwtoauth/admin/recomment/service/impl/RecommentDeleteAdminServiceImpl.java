package com.example.spacecommunitybackendjwtoauth.admin.recomment.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.recomment.service.RecommentDeleteAdminService;
import com.example.spacecommunitybackendjwtoauth.community.recomment.exception.RecommentNotExistException;
import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.repository.RecommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommentDeleteAdminServiceImpl implements RecommentDeleteAdminService {

    private final RecommentRepository recommentRepository;

    @Override
    public void deleteRecomment(Long recommentId) {
        if(!recommentRepository.existsById(recommentId)) throw new RecommentNotExistException();
        recommentRepository.deleteById(recommentId);
    }
}
