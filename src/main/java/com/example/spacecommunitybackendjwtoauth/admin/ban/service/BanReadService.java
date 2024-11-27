package com.example.spacecommunitybackendjwtoauth.admin.ban.service;

import com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.dto.BanReadDTO;

import java.util.List;

public interface BanReadService {
    List<BanReadDTO> getBanList();
}
