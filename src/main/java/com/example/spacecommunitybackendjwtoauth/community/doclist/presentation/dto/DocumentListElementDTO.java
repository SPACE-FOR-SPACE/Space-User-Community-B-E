package com.example.spacecommunitybackendjwtoauth.community.doclist.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "문서의 리스트의 Data Transfer Object 입니다.")
public record DocumentListElementDTO(
        @Schema(description = "문서의 ID")
        Long documentId,
        @Schema(description = "문서의 제목")
        String title,
        @Schema(description = "작성자의 이름")
        String authorName,
        @Schema(description = "문사의 카테고리")
        String category,
        @Schema(description = "문서의 아이콘")
        Long icon,
        @Schema(description = "문서 생성 날짜")
        String date
) { }
