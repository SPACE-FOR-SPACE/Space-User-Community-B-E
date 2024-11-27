package com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공지를 읽는 Data Transfer Object 입니다.")
public record BroadcastReadDTO(
        @Schema(description = "공지 ID")
        Long id,
        @Schema(description = "공지 제목")
        String title,
        @Schema(description = "공지 내용")
        String contents,
        @Schema(description = "공지 작성자 이름")
        String authorName,
        @Schema(description = "공지 카테고리")
        String category,
        @Schema(description = "공지 생성날짜")
        String date
) {}