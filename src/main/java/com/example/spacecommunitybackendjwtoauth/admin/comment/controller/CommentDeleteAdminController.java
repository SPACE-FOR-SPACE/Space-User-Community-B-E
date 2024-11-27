package com.example.spacecommunitybackendjwtoauth.admin.comment.controller;

import com.example.spacecommunitybackendjwtoauth.admin.comment.service.CommentDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentDeleteAdminController {
    private final CommentDeleteService commentDeleteAdminService;
    @DeleteMapping("/admin/comment/{commentId}")
    public void deleteDocument(@PathVariable Long commentId) {
        if(commentId==null) throw new IllegalArgumentException();
        commentDeleteAdminService.deleteComment(commentId);
    }
}
