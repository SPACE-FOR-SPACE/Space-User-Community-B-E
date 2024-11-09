package com.example.spacecommunitybackendjwtoauth.auth.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// 회원가입 DTO
public record JoinUserDTO(
        @Schema(description = "사용자 이메일", example = "user@gmail.com", required = true)
        String email,
        @Schema(description = "사용자 이름", example = "user", required = true)
        String username,
        @Schema(description = "사용자 비밀번호", example = "1234", required = true)
        String password,
        @Schema(description = "사용자 나이", example = "12", required = true)
        String age
) {}