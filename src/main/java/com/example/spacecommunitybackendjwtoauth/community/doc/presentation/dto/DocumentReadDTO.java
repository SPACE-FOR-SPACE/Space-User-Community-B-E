package com.example.spacecommunitybackendjwtoauth.community.doc.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "문서를 읽는 Data Transfer Object 입니다.")
public record DocumentReadDTO(
        @Schema(description = "문서의 ID")
        Long documentId,
        @Schema(description = "작성자의 이름")
        String authorName,
        @Schema(description = "문서의 제목")
        String title,
        @Schema(description = "문서의 내용")
        String content,
        @Schema(description = "문서의 아이콘")
        Long icon,
        @Schema(description = "문서의 좋아요 수")
        Long likes,
        @Schema(description = "문사의 카테고리")
        String category,
        @Schema(description = "사용자의 문서 좋아요 여부")
        Boolean likeStatus,
        @Schema(description = "문서 생성 날짜")
        String date
) { }
