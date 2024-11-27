package com.example.spacecommunitybackendjwtoauth.report.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.report.domain.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    List<ReportEntity> findAllByOrderByCreatedAt();
}