package com.example.spacecommunitybackendjwtoauth.report.service.impl;

import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.report.domain.ReportEntity;
import com.example.spacecommunitybackendjwtoauth.report.presentation.dto.ReportDTO;
import com.example.spacecommunitybackendjwtoauth.report.presentation.repository.ReportRepository;
import com.example.spacecommunitybackendjwtoauth.report.service.UserReportService;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserNotExistException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserReportServiceImpl implements UserReportService {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public void generateReport(HttpServletRequest request, ReportDTO reportDTO) {
        String accessToken = jwtUtil.getAccessTokenFromHeaders(request).replaceFirst("Bearer ", "");
        Long userId = jwtUtil.getUserId(accessToken);
        UserEntity author = userRepository.findById(userId).orElseThrow(UserNotExistException::new);
        reportRepository.save(ReportEntity.builder()
                .title(reportDTO.title())
                .contents(reportDTO.contents())
                .author(author)
                .build());
    }
}
