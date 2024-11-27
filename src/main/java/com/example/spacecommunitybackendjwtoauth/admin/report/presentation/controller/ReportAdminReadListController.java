package com.example.spacecommunitybackendjwtoauth.admin.report.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.admin.report.presentation.dto.ReportReadListDTO;
import com.example.spacecommunitybackendjwtoauth.admin.report.service.ReportReadListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReportAdminReadListController {

    private final ReportReadListService reportReadListService;

    @GetMapping("/reportlist")
    public List<ReportReadListDTO> reportList(){
        return reportReadListService.getAllReports();
    }
}
