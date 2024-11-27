package com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "대댓글 읽기를 위한 Data Transfer Object 입니다.")
public record RecommentReadDTO(
        @Schema(description = "대댓글 ID")
        Long id,
        @Schema(description = "작성자 이름")
        String authorName,
        @Schema(description = "대댓글 내용")
        String content,
        @Schema(description = "대댓글 날짜")
        String date
) { }
