package com.example.spacecommunitybackendjwtoauth.community.comment.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "댓글을 생성하는 Data Transfer Object 입니다.")
public record CommentCreateDTO(
        @Schema(description = "문서의 ID")
        Long documentId,
        @Schema(description = "댓글의 내용")
        String content
) {}