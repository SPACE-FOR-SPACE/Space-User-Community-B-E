package com.example.spacecommunitybackendjwtoauth.user.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.user.presentation.dto.UserUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.user.service.UserUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserUpdateController {

    private final UserUpdateService userUpdateService;

    @PatchMapping("/update")
    @Operation(summary = "사용자 정보 업데이트", description = "사용자의 정보 업데이트")
    public ResponseEntity<?> updateUser(
            @Parameter(description = "HTTP 요청")
            HttpServletRequest request,

            @Parameter(description = "HTTP 응답")
            HttpServletResponse response,

            @Parameter(description = "업데이트할 사용자의 정보")
            @RequestPart(required = false)
            UserUpdateDTO userUpdate,

            @Parameter(description = "사용자 프로필 사진")
            @RequestPart(value = "profile", required = false)
            MultipartFile profile
    ) throws IOException
    {
        userUpdateService.updateUser(request, response, userUpdate, profile);
        return ResponseEntity.ok("User successfully updated.");
    }
}
