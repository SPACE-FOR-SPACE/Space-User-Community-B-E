package com.example.spacecommunitybackendjwtoauth.admin.ban.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.ban.domain.BanEntity;
import com.example.spacecommunitybackendjwtoauth.admin.ban.exception.BannedUserException;
import com.example.spacecommunitybackendjwtoauth.admin.ban.exception.NotBanUserException;
import com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.repository.BanRepository;
import com.example.spacecommunitybackendjwtoauth.admin.ban.service.BanService;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotExistException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BanServiceImpl implements BanService {
    private final BanRepository banRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void addBanList(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UserNotExistException();
        boolean ban = banRepository.existsByUser(user.get());
        if(ban) throw new BannedUserException();
        banRepository.save(BanEntity.builder().user(user.get()).build());
    }

    @Transactional
    @Override
    public void removeBanList(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UserNotExistException();
        boolean ban = banRepository.existsByUser(user.get());
        if(!ban) throw new NotBanUserException();
        banRepository.deleteByUserUsername(username);
    }
}
