package com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "밴이 된 유저에 관한 Data Transfer Object 입니다.")
public record BanReadDTO(
        @Schema(description = "유저 이름")
        String username,
        @Schema(description = "밴한 날짜")
        String date
) { }