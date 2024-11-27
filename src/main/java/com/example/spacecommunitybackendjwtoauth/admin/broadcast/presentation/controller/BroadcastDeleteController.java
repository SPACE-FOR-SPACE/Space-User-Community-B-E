package com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.BroadcastDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BroadcastDeleteController {

    private final BroadcastDeleteService broadcastDeleteService;

    @DeleteMapping("/broadcast/{broadcastId}")
    public void deleteBroadcast(@PathVariable Long broadcastId) {
        if(broadcastId==null) throw new IllegalArgumentException();
        broadcastDeleteService.deleteBroadcast(broadcastId);
    }
}
