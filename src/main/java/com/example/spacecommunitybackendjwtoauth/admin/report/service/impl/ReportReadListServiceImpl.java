package com.example.spacecommunitybackendjwtoauth.admin.report.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.report.presentation.dto.ReportReadListDTO;
import com.example.spacecommunitybackendjwtoauth.admin.report.service.ReportReadListService;
import com.example.spacecommunitybackendjwtoauth.report.domain.ReportEntity;
import com.example.spacecommunitybackendjwtoauth.report.presentation.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportReadListServiceImpl implements ReportReadListService {

    private final ReportRepository reportRepository;

    @Override
    public List<ReportReadListDTO> getAllReports() {
        List<ReportEntity> reportEntities = reportRepository.findAllByOrderByCreatedAt();
        return reportEntities.stream().map(r -> new ReportReadListDTO(r.getReportId(), r.getTitle(), r.getAuthor().getUsername())).toList();
    }
}
