package com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.broadcast.exception.BroadcastNotExistException;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.dto.BroadcastUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.repository.BroadcastRepository;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.BroadcastUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BroadcastUpdateServiceImpl implements BroadcastUpdateService {

    private final BroadcastRepository broadcastRepository;

    @Override
    public void updateBroadcast(BroadcastUpdateDTO broadcastUpdateDTO) {
        if(!broadcastRepository.existsById(broadcastUpdateDTO.id())) throw new BroadcastNotExistException();
        broadcastRepository.updateById(broadcastUpdateDTO.title(), broadcastUpdateDTO.contents(), broadcastUpdateDTO.id());
    }
}
