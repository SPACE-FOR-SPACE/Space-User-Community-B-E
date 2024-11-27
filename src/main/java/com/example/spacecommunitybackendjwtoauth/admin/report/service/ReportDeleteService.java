package com.example.spacecommunitybackendjwtoauth.admin.report.service;

import jakarta.mail.MessagingException;

public interface ReportDeleteService {
    void ReportDeleteById(Long reportId) throws MessagingException;
}
