package com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.dto.RecommentReadDTO;
import com.example.spacecommunitybackendjwtoauth.community.recomment.service.RecommentReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class RecommentReadController {

    private final RecommentReadService recommentReadService;

    @GetMapping("/recomment/{commentID}")
    public List<RecommentReadDTO> getComments(@PathVariable Long commentID) {
        if(commentID == null) throw new IllegalArgumentException();
        return recommentReadService.getAllRecomments(commentID);
    }
}
