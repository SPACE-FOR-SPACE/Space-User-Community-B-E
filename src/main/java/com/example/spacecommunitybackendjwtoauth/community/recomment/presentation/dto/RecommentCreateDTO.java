package com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "대댓글 생성을 위한 Data Transfer Object 입니다.")
public record RecommentCreateDTO(
        @Schema(description = "댓글 ID")
        Long commentId,
        @Schema(description = "대댓글 내용")
        String content
) {}