package com.example.spacecommunityuserbe.repository;

import com.example.spacecommunityuserbe.dto.RecommentDTO;
import com.example.spacecommunityuserbe.entity.RecommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommentRepository extends JpaRepository<RecommentEntity, Long> {
  List<RecommentDTO> findAllByDocumentIdAndCommentId(Long documentId, Long commentId);
  RecommentDTO findByDocumentIdAndCommentIdAndId(Long documentId, Long commentId, Long Id);
  void deleteByDocumentIdAndCommentIdAndId(Long documentId, Long commentId, Long Id);
}
