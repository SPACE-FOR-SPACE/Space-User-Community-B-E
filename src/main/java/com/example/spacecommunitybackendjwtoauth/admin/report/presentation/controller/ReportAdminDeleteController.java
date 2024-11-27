package com.example.spacecommunitybackendjwtoauth.admin.report.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.admin.report.service.ReportDeleteService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReportAdminDeleteController {

    private final ReportDeleteService reportDeleteService;

    @DeleteMapping("/report/{reportId}")
    public void deleteReportById(@PathVariable Long reportId) throws MessagingException {
        if(reportId==null) throw new IllegalArgumentException();
        reportDeleteService.ReportDeleteById(reportId);
    }
}
