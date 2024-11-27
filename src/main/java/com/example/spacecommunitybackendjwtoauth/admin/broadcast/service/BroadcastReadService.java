package com.example.spacecommunitybackendjwtoauth.admin.broadcast.service;

import com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.dto.BroadcastReadDTO;

import java.util.List;

public interface BroadcastReadService {
    List<BroadcastReadDTO> getAllBroadcasts();
}
