package com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.admin.ban.service.BanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class BanController {
    private final BanService banService;

    @GetMapping("/ban/{username}")
    public void addBanList(@PathVariable String username) {
        if(username == null) throw new IllegalArgumentException();
        banService.addBanList(username);
    }

    @GetMapping("/unban/{username}")
    public void removeBanList(@PathVariable String username) {
        if(username == null) throw new IllegalArgumentException();
        banService.removeBanList(username);
    }

}
