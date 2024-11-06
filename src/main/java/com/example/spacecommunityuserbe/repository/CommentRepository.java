package com.example.spacecommunityuserbe.repository;

import com.example.spacecommunityuserbe.dto.CommentDTO;
import com.example.spacecommunityuserbe.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
  CommentEntity save(CommentEntity commentEntity);
  CommentDTO findCommentsDTOById(Long id);
  CommentDTO findCommentsDTOByIdAndDocumentId(Long id, Long documentId);
  List<CommentDTO> findCommentsDTOByDocumentId(Long documentId);
  void deleteByIdAndDocumentId(Long id, Long documentId);
}
