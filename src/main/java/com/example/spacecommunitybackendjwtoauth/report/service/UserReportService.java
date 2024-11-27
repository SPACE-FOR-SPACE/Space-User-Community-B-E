package com.example.spacecommunitybackendjwtoauth.report.service;

import com.example.spacecommunitybackendjwtoauth.report.presentation.dto.ReportDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface UserReportService {
    void generateReport(HttpServletRequest request, ReportDTO reportDTO);
}
