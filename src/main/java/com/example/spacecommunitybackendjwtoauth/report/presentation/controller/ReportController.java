package com.example.spacecommunitybackendjwtoauth.report.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.report.presentation.dto.ReportDTO;
import com.example.spacecommunitybackendjwtoauth.report.service.UserReportService;
import com.example.spacecommunitybackendjwtoauth.util.NotNullUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class ReportController {
    private final UserReportService userReportService;

    @PostMapping("/report")
    @Operation(summary = "신고 요청", description = "신고를 요청합니다.")
    public ResponseEntity<?> userReport(
            @Parameter(description = "신고할 정보", required = true)
            @RequestBody
            ReportDTO reportDTO,

            @Parameter(description = "HTTP 요청")
            HttpServletRequest request
    )
    {
        NotNullUtil.hasNullFields(reportDTO);
        userReportService.generateReport(request, reportDTO);
        return ResponseEntity.ok("Report successfully created.");
    }
}
