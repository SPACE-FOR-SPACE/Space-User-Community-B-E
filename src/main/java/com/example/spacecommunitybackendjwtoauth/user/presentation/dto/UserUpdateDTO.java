package com.example.spacecommunitybackendjwtoauth.user.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserUpdateDTO(
        @Schema(description = "사용자 이메일", example = "user@gmail.com", required = true)
        String email,
        @Schema(description = "사용자 이름", example = "user", required = true)
        String username,
        @Schema(description = "사용자 비밀번호", example = "1234", required = true)
        String password
) { }