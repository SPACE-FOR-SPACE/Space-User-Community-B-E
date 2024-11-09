package com.example.spacecommunitybackendjwtoauth.user.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.user.presentation.dto.UserUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.user.service.DeleteUserService;
import com.example.spacecommunitybackendjwtoauth.user.service.ProfileUserService;
import com.example.spacecommunitybackendjwtoauth.user.service.UpdateUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UpdateUserService updateUserService;
    private final DeleteUserService deleteUserService;
    private final ProfileUserService profileUserService;

    @GetMapping("/profile")
    @Operation(summary = "사용자 정보 확인", description = "사용자의 정보를 확인합니다.")
    public ResponseEntity<?> profileUser(@RequestParam Long userId) {
        if(userId == null) throw new IllegalArgumentException();
        return profileUserService.profileUser(userId);
    }

    @PatchMapping("/update")
    @Operation(summary = "사용자 정보 업데이트", description = "사용자의 정보를 업데이트합니다.")
    public void updateUser(
            @Parameter(description = "HTTP 요청")
            HttpServletRequest request,

            @Parameter(description = "업데이트할 사용자의 정보", required = true)
            @RequestPart(value = "updateDTO")
            UserUpdateDTO userUpdate,

            @RequestPart(value = "profile", required = false)
            @Schema(description = "프로필 URL", example = "https://aws.com/1")
            MultipartFile profile
    ) throws IOException
    {
        updateUserService.updateUser(request, userUpdate, profile);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "사용자 제거", description = "사용자를 제거합니다.")
    public void deleteUser(
            @Parameter(description = "HTTP 요청") HttpServletRequest request
    ) {
        deleteUserService.deleteUser(request);
    }
}
