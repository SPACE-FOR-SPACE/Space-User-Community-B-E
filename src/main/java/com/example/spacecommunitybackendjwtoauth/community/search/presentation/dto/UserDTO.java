package com.example.spacecommunitybackendjwtoauth.community.search.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User에 관한 Data Transfer Object 입니다.")
public record UserDTO(
        @Schema(description = "유저 ID")
        Long userId,
        @Schema(description = "유저 이름")
        String username,
        @Schema(description = "유저 프로필")
        String profile,
        @Schema(description = "유저 가입 날짜")
        String createdAt
)
{ }