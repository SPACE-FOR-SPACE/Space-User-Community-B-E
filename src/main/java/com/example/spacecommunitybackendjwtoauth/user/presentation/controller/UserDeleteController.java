package com.example.spacecommunitybackendjwtoauth.user.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.user.service.UserDeleteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserDeleteController {

    private final UserDeleteService userDeleteService;

    @DeleteMapping("/delete")
    @Operation(summary = "사용자 계정 삭제", description = "사용자 계정 삭제")
    public ResponseEntity<?> deleteUser(
            @Parameter(description = "HTTP 요청")
            HttpServletRequest request
    ) {
        userDeleteService.deleteUser(request);
        return ResponseEntity.ok("User successfully deleted.");
    }
}
