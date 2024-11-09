package com.example.spacecommunitybackendjwtoauth.auth.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.auth.service.implementation.ReIssuer;
import com.example.spacecommunitybackendjwtoauth.auth.service.implementation.UserJoinService;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.JoinUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AuthController {

    private final UserJoinService userJoinService;
    private final ReIssuer reIssuer;

    // 회원가입 Controller
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "사용자 가입", description = "사용자를 가입시킵니다.")
    public void joinProcess(
            @Parameter(description = "가입할 사용자 정보", required = true)
            @RequestBody JoinUserDTO joinUserRequest
    ) {
        userJoinService.joinProcess(joinUserRequest);
    }

    // 토큰 재발급 Controller
    @GetMapping("/reissue")
    @Operation(summary = "토큰 재발급", description = "JWT 토큰을 재발급합니다.")
    public ResponseEntity<?> reissue(
            @Parameter(description = "HTTP 요청") HttpServletRequest request,
            @Parameter(description = "HTTP 응답") HttpServletResponse response
    ) {
        return reIssuer.reissue(request, response);
    }
}
