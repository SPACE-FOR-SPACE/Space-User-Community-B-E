package com.example.spacecommunitybackendjwtoauth.report.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "신고 게시글을 위한 Data Transfer Object 입니다.")
public record ReportDTO(
    @Schema(description = "신고 게시글 제목", required = true)
    String title,
    @Schema(description = "신고 게시글 내용", required = true)
    String contents
) { }
