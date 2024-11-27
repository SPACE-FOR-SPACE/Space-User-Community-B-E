package com.example.spacecommunitybackendjwtoauth.community.like.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.like.presentation.dto.LikeDTO;
import com.example.spacecommunitybackendjwtoauth.community.like.service.LikeService;
import com.example.spacecommunitybackendjwtoauth.util.NotNullUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/doc")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like")
    @Operation(summary = "문서 좋아요", description = "문서 좋아요")
    public void likeDocument(@RequestBody LikeDTO likeDTO) {
        NotNullUtil.hasNullFields(likeDTO);
        likeService.likeDocument(likeDTO);
    }

    @PostMapping("/unlike")
    @Operation(summary = "문서 좋아요 취소", description = "문서 좋아요 취소")
    public void unlikeDocument(@RequestBody LikeDTO likeDTO) {
        NotNullUtil.hasNullFields(likeDTO);
        likeService.unlikeDocument(likeDTO);
    }

}
