package com.example.spacecommunitybackendjwtoauth.community.comment.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.comment.presentation.dto.CommentCreateDTO;
import com.example.spacecommunitybackendjwtoauth.community.comment.service.CommentCreateService;
import com.example.spacecommunitybackendjwtoauth.util.NotNullUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommentCreateController {

    private final CommentCreateService commentCreateService;

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(HttpServletRequest request, @RequestBody CommentCreateDTO createDTO) {
        NotNullUtil.hasNullFields(createDTO);
        commentCreateService.createComment(request, createDTO);
        return ResponseEntity.ok("Comment successfully created");
    }
}
