package com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.dto.BanReadDTO;
import com.example.spacecommunitybackendjwtoauth.admin.ban.service.BanReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class BanReadController {
    private final BanReadService banReadService;

    @GetMapping("/banlist")
    public List<BanReadDTO> getBanList(){
        return banReadService.getBanList();
    }

}
