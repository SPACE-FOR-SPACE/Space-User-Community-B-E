package com.example.spacecommunitybackendjwtoauth.admin.report.service;

import com.example.spacecommunitybackendjwtoauth.admin.report.presentation.dto.ReportReadListDTO;

import java.util.List;

public interface ReportReadListService {
    List<ReportReadListDTO> getAllReports();
}
