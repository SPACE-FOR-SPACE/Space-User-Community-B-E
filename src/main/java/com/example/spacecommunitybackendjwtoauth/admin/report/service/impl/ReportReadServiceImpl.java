package com.example.spacecommunitybackendjwtoauth.admin.report.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.report.exception.ReportNotExistException;
import com.example.spacecommunitybackendjwtoauth.admin.report.presentation.dto.ReportReadDTO;
import com.example.spacecommunitybackendjwtoauth.admin.report.service.ReportReadService;
import com.example.spacecommunitybackendjwtoauth.report.domain.ReportEntity;
import com.example.spacecommunitybackendjwtoauth.report.presentation.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportReadServiceImpl implements ReportReadService {

    private final ReportRepository reportRepository;

    @Override
    public ReportReadDTO getReportById(Long reportId) {
        ReportEntity reportEntity = reportRepository.findById(reportId).orElseThrow(ReportNotExistException::new);
        return new ReportReadDTO(reportEntity.getReportId(), reportEntity.getTitle(), reportEntity.getContents(), reportEntity.getAuthor().getUsername());
    }
}
