package com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공지 생성 Data Transfer Object 입니다.")
public record BroadcastCreateDTO(
        @Schema(description = "공지 제목")
        String title,
        @Schema(description = "공지 내용")
        String contents
) {}