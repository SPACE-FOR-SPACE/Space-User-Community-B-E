package com.example.spacecommunityuserbe.repository;

import com.example.spacecommunityuserbe.dto.DocumentDTO;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
  Optional<DocumentEntity> findById(Long id);
  DocumentEntity save(DocumentEntity documentEntity);
  DocumentDTO findDocumentDTOById(@Param("id") Long id);
  List<DocumentEntity> findAllByTitleContaining(@Param("title") String title);
  List<DocumentEntity> findAllByOrderByCreatedAtAsc();
  List<DocumentEntity> findAllByOrderByLikesDesc();
  List<DocumentEntity> findAllByUserIdOrderByCreatedAtAsc(@Param("userId") Long userId);
}
