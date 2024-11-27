package com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.dto.BroadcastReadDTO;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.repository.BroadcastRepository;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.BroadcastReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BroadcastReadServiceImpl implements BroadcastReadService {

    private final BroadcastRepository broadcastRepository;

    @Override
    public List<BroadcastReadDTO> getAllBroadcasts() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return broadcastRepository.findAll(sort).stream().map(b -> new BroadcastReadDTO(
                b.getId(),
                b.getTitle(),
                b.getContents(),
                b.getAuthor().getUsername(),
                "공지",
                b.getCreatedAt().toString()
        )).toList();
    }
}
