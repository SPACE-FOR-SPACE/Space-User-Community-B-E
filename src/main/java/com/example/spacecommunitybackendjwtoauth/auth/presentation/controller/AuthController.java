package com.example.spacecommunitybackendjwtoauth.auth.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.EmailSendDTO;
import com.example.spacecommunitybackendjwtoauth.auth.service.EmailSendService;
import com.example.spacecommunitybackendjwtoauth.auth.service.ReIssuer;
import com.example.spacecommunitybackendjwtoauth.auth.service.UserJoinMailService;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.UserJoinDTO;
import com.example.spacecommunitybackendjwtoauth.util.NotNullUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.mail.MessagingException;
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

    private final UserJoinMailService userJoinMailService;
    private final EmailSendService emailSendService;
    private final ReIssuer reIssuer;

    // 회원가입 Controller
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "사용자 가입", description = "사용자를 가입시킵니다.")
    public void joinProcess(
            @Parameter(description = "가입할 사용자 정보", required = true)
            @RequestBody UserJoinDTO joinUserRequest
    ) {
        userJoinMailService.joinProcess(joinUserRequest);
    }

    @PostMapping("/sendEmail")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "이메일 코드 전송", description = "이메일 코드를 전송합니다.")
    public void verifyEmail(@RequestBody EmailSendDTO email) throws MessagingException {
        NotNullUtil.hasNullFields(email);
        emailSendService.sendMail(email.email());
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
