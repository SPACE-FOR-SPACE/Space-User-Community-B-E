package com.example.spacecommunityuserbe.repository;

import com.example.spacecommunityuserbe.entity.LikesDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesDocumentRepository extends JpaRepository<LikesDocumentEntity, Long> {
  boolean existsByDocumentIdAndUserId(Long documentId, Long userId);
  void deleteByDocumentIdAndUserId(Long documentId, Long userId);
  LikesDocumentEntity save(LikesDocumentEntity likesDocumentEntity);
}
