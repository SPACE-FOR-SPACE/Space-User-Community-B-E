package com.example.spacecommunityuserbe.mapper;

import com.example.spacecommunityuserbe.dto.DocumentDTO;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {
  public DocumentEntity toDocumentEntity(DocumentDTO documentDTO) {
    return new DocumentEntity(
            documentDTO.id(),
            documentDTO.userId(),
            documentDTO.title(),
            documentDTO.content(),
            documentDTO.icon(),
            documentDTO.category(),
            documentDTO.likes(),
            documentDTO.createdAt()
    );
  }
  public DocumentDTO toDocumentDTO(DocumentEntity documentEntity) {
    return new DocumentDTO(
            documentEntity.getId(),
            documentEntity.getUserId(),
            documentEntity.getTitle(),
            documentEntity.getContent(),
            documentEntity.getIcon(),
            documentEntity.getCategory(),
            documentEntity.getLikes(),
            documentEntity.getCreatedAt());
      }
}
