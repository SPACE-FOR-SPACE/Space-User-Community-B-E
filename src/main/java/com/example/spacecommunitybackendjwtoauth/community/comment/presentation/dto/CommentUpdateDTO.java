package com.example.spacecommunitybackendjwtoauth.community.comment.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "댓글을 업데이트 하는 Data Transfer Object 입니다.")
public record CommentUpdateDTO(
        @Schema(description = "댓글의 ID")
        Long id,
        @Schema(description = "댓글의 내용")
        String content
) {}