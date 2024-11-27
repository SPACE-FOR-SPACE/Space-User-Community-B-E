package com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.broadcast.exception.BroadcastNotExistException;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.repository.BroadcastRepository;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.BroadcastDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BroadcastDeleteServiceImpl implements BroadcastDeleteService {

    private final BroadcastRepository broadcastRepository;

    @Override
    public void deleteBroadcast(Long id) {
        if(!broadcastRepository.existsById(id)) throw new BroadcastNotExistException();
        broadcastRepository.deleteById(id);
    }
}
