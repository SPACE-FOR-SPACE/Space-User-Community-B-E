package com.example.spacecommunityuserbe.repository;

import com.example.spacecommunityuserbe.dto.CommentDTO;
import com.example.spacecommunityuserbe.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
  CommentEntity save(CommentEntity commentEntity);
  @Query("SELECT c FROM CommentEntity c LEFT JOIN FETCH c.recomment WHERE c.id = :id AND c.document.id = :documentId")
  CommentEntity findByIdAndDocumentId(@Param("id") Long id, @Param("documentId") Long documentId);

  @Query("SELECT c FROM CommentEntity c LEFT JOIN FETCH c.recomment WHERE c.document.id = :documentId")
  List<CommentEntity> findCommentEntitiesByDocumentId(@Param("documentId") Long documentId);
  void deleteByIdAndDocumentId(Long id, Long documentId);
}
