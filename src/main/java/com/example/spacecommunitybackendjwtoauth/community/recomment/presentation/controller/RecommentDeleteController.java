package com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.recomment.service.RecommentDeleteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class RecommentDeleteController {

    private final RecommentDeleteService recommentDeleteService;

    @DeleteMapping("/recomment/{recommentID}")
    public void deleteComment(HttpServletRequest request, @PathVariable Long recommentID) {
        if(recommentID == null) throw new IllegalArgumentException();
        recommentDeleteService.deleteRecomment(request, recommentID);
    }
}
