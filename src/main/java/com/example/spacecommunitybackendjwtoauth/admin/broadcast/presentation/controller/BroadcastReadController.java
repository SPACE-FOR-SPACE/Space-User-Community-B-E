package com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.dto.BroadcastReadDTO;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.BroadcastReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BroadcastReadController {
    private final BroadcastReadService broadcastReadService;
    @GetMapping("/user/broadcast")
    public List<BroadcastReadDTO> getBroadcast() {
        return broadcastReadService.getAllBroadcasts();
    }
}
