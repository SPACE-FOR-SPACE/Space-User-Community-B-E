package com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.dto.BroadcastCreateDTO;
import com.example.spacecommunitybackendjwtoauth.admin.broadcast.service.BroadcastCreateService;
import com.example.spacecommunitybackendjwtoauth.util.NotNullUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BroadcastCreateController {

    private final BroadcastCreateService broadcastCreateService;

    @PostMapping("/broadcast")
    public void saveBroadcast(@RequestBody BroadcastCreateDTO broadcastCreateDTO, HttpServletRequest request) {
        NotNullUtil.hasNullFields(broadcastCreateDTO);
        broadcastCreateService.createBroadcast(broadcastCreateDTO.title(), broadcastCreateDTO.contents(), request);
    }
}
