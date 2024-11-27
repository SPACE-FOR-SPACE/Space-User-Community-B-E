package com.example.spacecommunitybackendjwtoauth.user.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.user.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/{username}")
    @Operation(summary = "사용자 정보 확인", description = "사용자의 정보 확인")
    public ResponseEntity<?> profileUser(
            @Parameter(description = "사용자의 이름")
            @PathVariable String username
    ) {
        if(username == null) throw new IllegalArgumentException();
        return ResponseEntity.ok(userProfileService.getUserProfile(username));
    }
}
