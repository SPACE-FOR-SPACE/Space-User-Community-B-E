package com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.dto.BroadcastUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.BroadcastUpdateService;
import com.example.spacecommunitybackendjwtoauth.util.NotNullUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BroadcastUpdateController {

    private final BroadcastUpdateService broadcastUpdateService;

    @PatchMapping("/broadcast")
    public void updateBroadcast(@RequestBody BroadcastUpdateDTO broadcastUpdateDTO) {
        NotNullUtil.hasNullFields(broadcastUpdateDTO);
        broadcastUpdateService.updateBroadcast(broadcastUpdateDTO);
    }
}
