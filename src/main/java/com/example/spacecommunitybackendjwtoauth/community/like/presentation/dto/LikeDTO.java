package com.example.spacecommunitybackendjwtoauth.community.like.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "문서의 좋아요에 대한 Data Transfer Object 입니다.")
public record LikeDTO(
        @Schema(description = "작성자의 ID")
        Long authorId,
        @Schema(description = "문서의 ID")
        Long documentId
) { }
