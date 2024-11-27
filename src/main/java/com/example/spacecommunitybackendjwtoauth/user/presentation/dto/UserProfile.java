package com.example.spacecommunitybackendjwtoauth.user.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 정보를 위한 Data Transfer Object 입니다.")
public record UserProfile(
        @Schema(description = "유저 프로필", example = "https://amazon.com")
        String profile,
        @Schema(description = "유저 자기소개", example = "안녕하세요 저는 베트남에서 온 응우옌이에요.")
        String introduce,
        @Schema(description = "유저 계정 생성일", example = "몇월 몇일 가입")
        String date
)
{ }
