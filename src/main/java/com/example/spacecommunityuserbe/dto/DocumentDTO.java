package com.example.spacecommunityuserbe.dto;

import com.example.spacecommunityuserbe.entity.DocumentIcon;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record DocumentDTO(
        @Schema(description = "문서 id(자동생성)", example = "10")
        Long id,

        @Schema(description = "유저 id", example = "123")
        Long userId,

        @Schema(description = "댓글 목록")
        List<CommentDTO> comments,

        @Schema(description = "문서 제목", example = "title")
        String title,

        @Schema(description = "문서 내용", example = "<h1>content</h1>")
        String content,

        @Schema(description = "문서 아이콘", example = "ICON1")
        DocumentIcon icon,

        @Schema(description = "문서 카테고리", example = "공지")
        String category,

        @Schema(description = "문서 좋아요 수(기본 0)", example = "15")
        Long likes,

        @Schema(description = "문서 작성 시간(자동생성)", example = "2024-11-05T20:17:37.788405")
        LocalDateTime createdAt
) {
  public DocumentDTO {
    if (likes == null) {
      likes = 0L;
    }
    createdAt = LocalDateTime.now();
    if (comments == null) {
      comments = new ArrayList<>();
    }
  }
}