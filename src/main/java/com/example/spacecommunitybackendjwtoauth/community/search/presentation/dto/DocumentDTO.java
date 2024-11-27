package com.example.spacecommunitybackendjwtoauth.community.search.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Document 를 보여주는 Data Transfer Object 입니다.")
public record DocumentDTO(
        @Schema(description = "문서 ID")
        Long docId,
        @Schema(description = "문서의 제목")
        String title,
        @Schema(description = "문서의 작성자 이름")
        String authorName,
        @Schema(description = "문서의 카테고리")
        String category,
        @Schema(description = "문서의 아이콘")
        Long icon,
        @Schema(description = "문서가 생긴 날짜")
        String createdAt
)
{ }