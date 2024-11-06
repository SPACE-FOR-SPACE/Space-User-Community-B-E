package com.example.spacecommunityuserbe.mapper;

import com.example.spacecommunityuserbe.dto.CommentDTO;
import com.example.spacecommunityuserbe.entity.CommentEntity;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
  public CommentEntity toCommentsEntity(CommentDTO commentDTO, DocumentEntity documentEntity) {
    return new CommentEntity(
            commentDTO.id(),
            commentDTO.userId(),
            documentEntity,
            commentDTO.content(),
            commentDTO.createdAt()
    );
  }

  public CommentDTO toCommentsDTO(CommentEntity commentEntity) {
    return new CommentDTO(
            commentEntity.getId(),
            commentEntity.getUserId(),
            commentEntity.getDocument().getId(),
            commentEntity.getContent(),
            commentEntity.getCreatedAt()
    );
  }
}
