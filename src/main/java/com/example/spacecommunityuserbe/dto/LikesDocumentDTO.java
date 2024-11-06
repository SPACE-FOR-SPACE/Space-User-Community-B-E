package com.example.spacecommunityuserbe.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LikesDocumentDTO(
        @Schema(description = "문서 좋아요 id(자동생성)", example = "3")
        Long id,

        @Schema(description = "유저 아이디", example = "123", required = true)
        Long userId,

        @Schema(description = "문서 아이디", example = "321", required = true)
        Long documentId
) {}