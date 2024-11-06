package com.example.spacecommunityuserbe.repository;

import com.example.spacecommunityuserbe.dto.CommentsDTO;
import com.example.spacecommunityuserbe.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {
  CommentsEntity save(CommentsEntity commentsEntity);
  CommentsDTO findCommentsDTOById(Long id);
  CommentsDTO findCommentsDTOByIdAndDocumentId(Long id, Long documentId);
  List<CommentsDTO> findCommentsDTOByDocumentId(Long documentId);
  void deleteByIdAndDocumentId(Long id, Long documentId);
}
