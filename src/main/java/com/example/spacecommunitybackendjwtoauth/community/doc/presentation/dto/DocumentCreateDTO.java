package com.example.spacecommunitybackendjwtoauth.community.doc.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "문서를 생성하는 Data Transfer Object 입니다.")
public record DocumentCreateDTO(
        @Schema(description = "작성자 ID")
        Long authorId,
        @Schema(description = "문서 제목")
        String title,
        @Schema(description = "문서 내용")
        String content,
        @Schema(description = "문서 아이콘")
        Long icon,
        @Schema(description = "문서 카테고리")
        String category
) { }
