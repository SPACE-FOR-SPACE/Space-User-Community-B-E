package com.example.spacecommunityuserbe.mapper;

import com.example.spacecommunityuserbe.dto.DocumentDTO;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {
  public DocumentEntity toDocumentEntity(DocumentDTO documentDTO) {
    return new DocumentEntity(
            documentDTO.getId(),
            documentDTO.getUserId(),
            documentDTO.getTitle(),
            documentDTO.getContent(),
            documentDTO.getIcon(),
            documentDTO.getCategory(),
            documentDTO.getLikes(),
            documentDTO.getCreatedAt()
    );
  }
  public DocumentDTO toDocumentDTO(DocumentEntity documentEntity) {
    return DocumentDTO.builder()
            .id(documentEntity.getId())
            .title(documentEntity.getTitle())
            .content(documentEntity.getContent())
            .icon(documentEntity.getIcon())
            .category(documentEntity.getCategory())
            .likes(documentEntity.getLikes())
            .createdAt(documentEntity.getCreatedAt())
            .userId(documentEntity.getUserId())
            .build();
  }
}
