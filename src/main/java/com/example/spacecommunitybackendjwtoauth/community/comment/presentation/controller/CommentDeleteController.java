package com.example.spacecommunitybackendjwtoauth.community.comment.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.comment.service.CommentDeleteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/comment")
public class CommentDeleteController {

    private final CommentDeleteService commentDeleteService;

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(HttpServletRequest request, @PathVariable Long commentId) {
        if(commentId == null) throw new IllegalArgumentException();
        commentDeleteService.commentDelete(request, commentId);
        return ResponseEntity.ok("Comment successfully deleted");
    }
}
