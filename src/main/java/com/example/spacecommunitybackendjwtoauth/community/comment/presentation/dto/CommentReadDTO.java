package com.example.spacecommunitybackendjwtoauth.community.comment.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "댓글을 읽어오는 Data Transfer Object 입니다.")
public record CommentReadDTO(
        @Schema(description = "댓글의 ID")
        Long id,
        @Schema(description = "작성자의 이름")
        String authorName,
        @Schema(description = "댓글의 내용")
        String content,
        @Schema(description = "대댓글의 개수")
        Long recommentNumber,
        @Schema(description = "댓글 작성한 시각")
        String date
) { }