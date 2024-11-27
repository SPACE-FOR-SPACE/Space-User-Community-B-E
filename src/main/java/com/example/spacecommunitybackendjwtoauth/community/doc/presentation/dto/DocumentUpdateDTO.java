package com.example.spacecommunitybackendjwtoauth.community.doc.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "문서를 업데이트하는 Data Transfer Object 입니다.")
public record DocumentUpdateDTO(
        @Schema(description = "문서의 ID")
        Long documentId,
        @Schema(description = "문서의 제목")
        String title,
        @Schema(description = "문서의 내용")
        String content,
        @Schema(description = "문서의 아이콘")
        Long icon,
        @Schema(description = "문서의 카테고리")
        String category
) { }
