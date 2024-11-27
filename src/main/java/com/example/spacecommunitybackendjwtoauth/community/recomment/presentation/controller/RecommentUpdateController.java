package com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.dto.RecommentUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.community.recomment.service.RecommentUpdateService;
import com.example.spacecommunitybackendjwtoauth.util.NotNullUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class RecommentUpdateController {

    private final RecommentUpdateService recommentUpdateService;

    @PutMapping("/recomment")
    public void updateComment(@RequestBody RecommentUpdateDTO commentDTO) {
        NotNullUtil.hasNullFields(commentDTO);
        recommentUpdateService.updateRecomment(commentDTO);
    }
}
