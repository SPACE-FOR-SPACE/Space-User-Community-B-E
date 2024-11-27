package com.example.spacecommunitybackendjwtoauth.admin.report.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "신고를 읽는 Data Transfer Object 입니다.")
public record ReportReadDTO(
        @Schema(description = "신고 ID")
        Long id,
        @Schema(description = "신고 제목")
        String title,
        @Schema(description = "신고 내욤")
        String contents,
        @Schema(description = "신고 작성자 이름")
        String authorName
) { }
