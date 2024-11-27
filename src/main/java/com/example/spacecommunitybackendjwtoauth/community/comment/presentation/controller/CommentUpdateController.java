package com.example.spacecommunitybackendjwtoauth.community.comment.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.dto.CommentUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.community.comment.service.CommentUpdateService;
import com.example.spacecommunitybackendjwtoauth.util.NotNullUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommentUpdateController {

    private final CommentUpdateService commentUpdateService;

    @PatchMapping("/comment")
    public ResponseEntity<?> updateComment(@RequestBody CommentUpdateDTO commentUpdateDTO) {
        NotNullUtil.hasNullFields(commentUpdateDTO);
        commentUpdateService.updateComment(commentUpdateDTO);
        return ResponseEntity.ok("Comment successfully updated");
    }
}
