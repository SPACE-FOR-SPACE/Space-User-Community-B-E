package com.example.spacecommunitybackendjwtoauth.jwt.record;

import io.swagger.v3.oas.annotations.media.Schema;

// Login User DTO
@Schema(description = "로그인에 대한 Data Transfer Object 입니다.")
public record LoginUserDTO(
        @Schema(description = "유저 닉네임")
        String username,
        @Schema(description = "유저 비밀번호")
        String password
) { }