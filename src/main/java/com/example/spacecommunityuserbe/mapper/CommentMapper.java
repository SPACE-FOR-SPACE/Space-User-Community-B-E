package com.example.spacecommunityuserbe.mapper;

import com.example.spacecommunityuserbe.dto.CommentDTO;
import com.example.spacecommunityuserbe.dto.RecommentDTO;
import com.example.spacecommunityuserbe.entity.CommentEntity;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import com.example.spacecommunityuserbe.entity.RecommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {
  @Autowired
  private RecommentMapper recommentMapper;

  public CommentEntity toCommentEntity(CommentDTO commentDTO, DocumentEntity documentEntity) {
    CommentEntity commentEntity = new CommentEntity(
            commentDTO.id(),
            commentDTO.userId(),
            documentEntity,
            null,
            commentDTO.content(),
            commentDTO.createdAt()
    );

    List<RecommentEntity> recommentEntities = recommentMapper.toRecommentEntityList(commentDTO.recomments(), documentEntity, commentEntity);
    commentEntity.setRecomment(recommentEntities);
    return commentEntity;
  }

  public CommentDTO toCommentDTO(CommentEntity commentEntity) {
    List<RecommentDTO> recommentDTOList = recommentMapper.toRecommentDTOList(commentEntity.getRecomment());
    return new CommentDTO(
            commentEntity.getId(),
            commentEntity.getUserId(),
            recommentDTOList,
            commentEntity.getContent(),
            commentEntity.getCreatedAt()
    );
  }

  public List<CommentDTO> toCommentDTOList(List<CommentEntity> commentEntities) {
    if(commentEntities == null) {
      commentEntities = new ArrayList<>();
    }
    return commentEntities.stream()
            .map(this::toCommentDTO)
            .collect(Collectors.toList());
  }

  // List<CommentDTO> -> List<CommentEntity>
  public List<CommentEntity> toCommentEntityList(List<CommentDTO> commentDTOs, DocumentEntity documentEntity) {
    if(commentDTOs == null) {
      commentDTOs = new ArrayList<>();
    }
    return commentDTOs.stream()
            .map(commentDTO -> toCommentEntity(commentDTO, documentEntity))
            .collect(Collectors.toList());
  }
}
