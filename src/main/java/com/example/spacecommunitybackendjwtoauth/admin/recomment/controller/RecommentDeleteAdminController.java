package com.example.spacecommunitybackendjwtoauth.admin.recomment.controller;

import com.example.spacecommunitybackendjwtoauth.admin.recomment.service.RecommentDeleteAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecommentDeleteAdminController {

    private final RecommentDeleteAdminService recommentDeleteAdminService;

    @DeleteMapping("/admin/recomment/{recommentId}")
    public void deleteRecomment(@PathVariable Long recommentId) {
        if(recommentId==null) throw new IllegalArgumentException();
        recommentDeleteAdminService.deleteRecomment(recommentId);
    }
}
