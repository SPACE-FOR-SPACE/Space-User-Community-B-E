package com.example.spacecommunityuserbe.repository;

import com.example.spacecommunityuserbe.dto.DocumentDTO;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
  Optional<DocumentEntity> findById(Long id);
  DocumentEntity save(DocumentEntity documentEntity);
  DocumentDTO findDocumentDTOById(Long id);
}
