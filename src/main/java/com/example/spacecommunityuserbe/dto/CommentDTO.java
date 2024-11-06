package com.example.spacecommunityuserbe.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record CommentDTO(
        @Schema(description = "댓글 아이디(자동생성)", example = "3")
        Long id,

        @Schema(description = "유저 아이디", example = "123")
        Long userId,

        @Schema(description = "문서 아이디", example = "323")
        Long documentId,

        @Schema(description = "댓글 내용", example = "배아파")
        String content,

        @Schema(description = "댓글 생성 시간(자동생성)", example = "2024-11-05T23:31:38.605701")
        LocalDateTime createdAt
) {
}
