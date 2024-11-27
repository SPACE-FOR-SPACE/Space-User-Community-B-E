package com.example.spacecommunitybackendjwtoauth.admin.report.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.admin.report.presentation.dto.ReportReadDTO;
import com.example.spacecommunitybackendjwtoauth.admin.report.service.ReportReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReportAdminReadController {

    private final ReportReadService reportReadService;

    @GetMapping("/report/{reportId}")
    public ReportReadDTO getReportById(@PathVariable Long reportId) {
        if (reportId == null) throw new IllegalArgumentException();
        return reportReadService.getReportById(reportId);
    }
}
