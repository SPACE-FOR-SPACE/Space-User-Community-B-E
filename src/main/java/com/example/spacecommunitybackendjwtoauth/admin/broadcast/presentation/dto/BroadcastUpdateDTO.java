package com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공지를 업데이트하는 Data Transfer Object 입니다.")
public record BroadcastUpdateDTO(
        @Schema(description = "공지 ID")
        Long id,
        @Schema(description = "공지 제목")
        String title,
        @Schema(description = "공지 내용")
        String contents
) {}