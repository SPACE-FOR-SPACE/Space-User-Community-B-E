package com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "대댓글 업데이트를 위한 Data Transfer Object 입니다.")
public record RecommentUpdateDTO(
        @Schema(description = "대댓글 ID")
        Long id,
        @Schema(description = "대댓글 내용")
        String content
){}