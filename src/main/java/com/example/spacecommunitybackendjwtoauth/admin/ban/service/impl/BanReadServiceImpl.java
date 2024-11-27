package com.example.spacecommunitybackendjwtoauth.admin.ban.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.ban.domain.BanEntity;
import com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.dto.BanReadDTO;
import com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.repository.BanRepository;
import com.example.spacecommunitybackendjwtoauth.admin.ban.service.BanReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BanReadServiceImpl implements BanReadService {
    private final BanRepository banRepository;
    @Override
    public List<BanReadDTO> getBanList() {
        List<BanEntity> banEntities = banRepository.findAll();
        return banEntities.stream().map(b -> new BanReadDTO(b.getUser().getUsername(), b.getCreatedAt().toString())).toList();
    }
}
