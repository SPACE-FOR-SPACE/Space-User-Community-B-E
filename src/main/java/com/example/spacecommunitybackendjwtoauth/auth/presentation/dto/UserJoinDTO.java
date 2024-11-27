package com.example.spacecommunitybackendjwtoauth.auth.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// 회원가입 DTO
@Schema(description = "사용자 회원가입을 위한 Data Transfer Object 입니다.")
public record UserJoinDTO(
        @Schema(description = "사용자 이메일", example = "user@gmail.com", required = true)
        String email,
        @Schema(description = "사용자 이름", example = "user", required = true)
        String username,
        @Schema(description = "사용자 비밀번호", example = "1234", required = true)
        String password,
        @Schema(description = "사용자 나이", example = "12", required = true)
        Long age,
        @Schema(description = "사용자 인증", example = "success", required = true)
        String token
) {}