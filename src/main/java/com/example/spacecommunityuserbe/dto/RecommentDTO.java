package com.example.spacecommunityuserbe.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record RecommentDTO(
        @Schema(description = "대댓글 아이디(자동생성)", example = "3")
        Long id,

        @Schema(description = "유저 아이디", example = "3")
        Long userId,

        @Schema(description = "댓글 아이디", example = "1123")
        Long commentId,

        @Schema(description = "대댓글 내용", example = "피곤하다")
        String content,

        @Schema(description = "대댓글 작성 시간(자동생성)", example = "2024-11-05T20:17:37.788405")
        LocalDateTime createdAt
) {
  public RecommentDTO {
    createdAt = LocalDateTime.now();
  }
}
