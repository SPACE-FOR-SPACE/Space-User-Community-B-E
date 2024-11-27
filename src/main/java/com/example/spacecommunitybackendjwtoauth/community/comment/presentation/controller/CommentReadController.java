package com.example.spacecommunitybackendjwtoauth.community.comment.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.comment.service.CommentReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/comment")
public class CommentReadController {

    private final CommentReadService commentReadService;

    @GetMapping("/{docId}")
    public ResponseEntity<?> getCommentsById(@PathVariable Long docId) {
        if(docId == null) throw new IllegalArgumentException();
        return ResponseEntity.ok(commentReadService.getAllComments(docId));
    }
}
