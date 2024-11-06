package com.example.spacecommunityuserbe.mapper;

import com.example.spacecommunityuserbe.dto.CommentsDTO;
import com.example.spacecommunityuserbe.dto.LikesDocumentDTO;
import com.example.spacecommunityuserbe.entity.CommentsEntity;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import com.example.spacecommunityuserbe.entity.LikesDocumentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentsMapper {
  public CommentsEntity toCommentsEntity(CommentsDTO commentsDTO, DocumentEntity documentEntity) {
    return new CommentsEntity(
            commentsDTO.id(),
            commentsDTO.userId(),
            documentEntity,
            commentsDTO.content(),
            commentsDTO.createdAt()
    );
  }

  public CommentsDTO toCommentsDTO(CommentsEntity commentsEntity) {
    return new CommentsDTO(
            commentsEntity.getId(),
            commentsEntity.getUserId(),
            commentsEntity.getDocument().getId(),
            commentsEntity.getContent(),
            commentsEntity.getCreatedAt()
    );
  }
}
