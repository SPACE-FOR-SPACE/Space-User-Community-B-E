package com.example.spacecommunityuserbe.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record CommentDTO(
        @Schema(description = "댓글 아이디(자동생성)", example = "3")
        Long id,

        @Schema(description = "유저 아이디", example = "123")
        Long userId,

        @Schema(description = "댓글의 대댓글 목록")
        List<RecommentDTO> recomments,

        @Schema(description = "댓글 내용", example = "배아파")
        String content,

        @Schema(description = "댓글 생성 시간(자동생성)", example = "2024-11-05T23:31:38.605701")
        LocalDateTime createdAt


) {
  public CommentDTO {
    createdAt = LocalDateTime.now();
    if(recomments == null) {
      recomments = new ArrayList<>();
    }
  }
}

