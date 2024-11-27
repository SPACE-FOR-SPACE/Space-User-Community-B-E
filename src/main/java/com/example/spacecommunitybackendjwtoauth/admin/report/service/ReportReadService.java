package com.example.spacecommunitybackendjwtoauth.admin.report.service;

import com.example.spacecommunitybackendjwtoauth.admin.report.presentation.dto.ReportReadDTO;

public interface ReportReadService {
    ReportReadDTO getReportById(Long reportId);
}
