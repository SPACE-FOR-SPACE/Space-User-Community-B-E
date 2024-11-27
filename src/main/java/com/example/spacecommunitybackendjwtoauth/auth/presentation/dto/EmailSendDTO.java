package com.example.spacecommunitybackendjwtoauth.auth.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// 회원가입 DTO
@Schema(description = "이메일 전송을 위한 Data Transfer Object 입니다.")
public record EmailSendDTO(
        @Schema(description = "사용자 이메일", example = "user@gmail.com", required = true)
        String email
) {}