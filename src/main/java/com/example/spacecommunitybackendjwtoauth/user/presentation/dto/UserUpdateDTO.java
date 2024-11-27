package com.example.spacecommunitybackendjwtoauth.user.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Optional;

@Schema(description = "사용자 정보를 업데이트하기 위한 Data Transfer Object 입니다.")
public record UserUpdateDTO(
        @Schema(description = "사용자 이름", example = "user", nullable = true)
        Optional<String> username,
        @Schema(description = "사용자 이전 비밀번호", example = "1234", nullable = true)
        Optional<String> beforePassword,
        @Schema(description = "사용자 새 비밀번호", example = "123456", nullable = true)
        Optional<String> afterPassword,
        @Schema(description = "사용자 자기소개", example = "안녕하세요, 저는 베트남에서 온 응우옌이에요.", nullable = true)
        Optional<String> introduce
) { }