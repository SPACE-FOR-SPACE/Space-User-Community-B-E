package com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.dto.RecommentCreateDTO;
import com.example.spacecommunitybackendjwtoauth.community.recomment.service.RecommentCreateService;
import com.example.spacecommunitybackendjwtoauth.util.NotNullUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class RecommentCreateController {

    private final RecommentCreateService recommentCreateService;

    @PostMapping("/recomment")
    public void addComment(HttpServletRequest request, @RequestBody RecommentCreateDTO commentDTO) {
        NotNullUtil.hasNullFields(commentDTO);
        recommentCreateService.createRecomment(request, commentDTO);
    }
}
