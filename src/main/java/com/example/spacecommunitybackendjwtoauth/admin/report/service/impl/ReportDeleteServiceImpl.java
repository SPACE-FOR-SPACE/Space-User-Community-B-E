package com.example.spacecommunitybackendjwtoauth.admin.report.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.report.exception.ReportNotExistException;
import com.example.spacecommunitybackendjwtoauth.admin.report.service.ReportDeleteService;
import com.example.spacecommunitybackendjwtoauth.report.domain.ReportEntity;
import com.example.spacecommunitybackendjwtoauth.report.presentation.repository.ReportRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportDeleteServiceImpl implements ReportDeleteService {

    private final ReportRepository reportRepository;
    private final JavaMailSender mailSender;

    @Value("${AdminMail.id}")
    private String Adminemail;

    @Override
    public void ReportDeleteById(Long reportId) throws MessagingException {
        ReportEntity reportEntity = reportRepository.findById(reportId).orElseThrow(ReportNotExistException::new);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        String message = "<body style='background-color: rgb(237, 237, 237); display:flex; justify-content: center;align-items: center;align-content: center;'>" +
                "<div style='width:500px; padding:20px; background-color: rgb(255, 255, 255);text-align:center;'>" +
                "<h1 style='color: #301C86; text-align:center;'>SPACE COMMUNITY</h1>" +
                "<p style='text-align:center;'>" + reportEntity.getAuthor().getUsername() + "님 신고해주셔서 감사합니다.</p>" +
                "<p style='text-align:center;'>신고해주신 내용을 토대로 저희 운영진에서 신고에 대한 내용을 처리하였습니다.</p>" +
                "<p style='text-align:center;'>앞으로도 좋은 이용 부탁드립니다. 감사합니다.</p>" +
                "<p style='text-align:center;'>- 운영자 -</p>" +
                "</div>" +
                "</body>";

        helper.setTo(reportEntity.getAuthor().getEmail());
        helper.setFrom(Adminemail);
        helper.setSubject("SpaceCommunity 이메일 검증");
        helper.setText(message, true);

        mailSender.send(mimeMessage);
        reportRepository.deleteById(reportId);
    }
}
