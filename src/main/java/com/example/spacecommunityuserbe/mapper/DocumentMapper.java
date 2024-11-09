package com.example.spacecommunityuserbe.mapper;

import com.example.spacecommunityuserbe.dto.CommentDTO;
import com.example.spacecommunityuserbe.dto.DocumentDTO;
import com.example.spacecommunityuserbe.entity.CommentEntity;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentMapper {
  @Autowired
  private CommentMapper commentMapper;

  public DocumentEntity toDocumentEntity(DocumentDTO documentDTO) {
    DocumentEntity documentEntity = new DocumentEntity(
            documentDTO.id(),
            documentDTO.userId(),
            null,
            documentDTO.title(),
            documentDTO.content(),
            documentDTO.icon(),
            documentDTO.category(),
            documentDTO.likes(),
            documentDTO.createdAt()
    );

    List<CommentEntity> commentEntities  = commentMapper.toCommentEntityList(documentDTO.comments(), documentEntity);
    documentEntity.setComments(commentEntities);

    return documentEntity;
  }
  public DocumentDTO toDocumentDTO(DocumentEntity documentEntity) {
    List<CommentDTO> commentDTOList = commentMapper.toCommentDTOList(documentEntity.getComments());

    return new DocumentDTO(
            documentEntity.getId(),
            documentEntity.getUserId(),
            commentDTOList,
            documentEntity.getTitle(),
            documentEntity.getContent(),
            documentEntity.getIcon(),
            documentEntity.getCategory(),
            documentEntity.getLikes(),
            documentEntity.getCreatedAt());
  }
  public List<DocumentDTO> toDocumentDTOList(List<DocumentEntity> documentEntities) {
    if(documentEntities == null) {
      documentEntities = new ArrayList<>();
    }
    return documentEntities.stream()
            .map(this::toDocumentDTO)
            .collect(Collectors.toList());
  }
}
