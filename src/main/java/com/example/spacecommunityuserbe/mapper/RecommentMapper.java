package com.example.spacecommunityuserbe.mapper;

import com.example.spacecommunityuserbe.dto.CommentDTO;
import com.example.spacecommunityuserbe.dto.RecommentDTO;
import com.example.spacecommunityuserbe.entity.CommentEntity;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import com.example.spacecommunityuserbe.entity.RecommentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecommentMapper {
  public RecommentEntity toRecommentEntity(RecommentDTO recommentDTO, DocumentEntity documentEntity, CommentEntity commentEntity) {
    return new RecommentEntity(
            recommentDTO.id(),
            recommentDTO.userId(),
            documentEntity,
            commentEntity,
            recommentDTO.content(),
            recommentDTO.createdAt()
    );
  }

  public RecommentDTO toRecommentDTO(RecommentEntity recommentEntity) {
    return new RecommentDTO(
            recommentEntity.getId(),
            recommentEntity.getUserId(),
            recommentEntity.getComment().getId(),
            recommentEntity.getContent(),
            recommentEntity.getCreatedAt()
    );
  }

  public List<RecommentDTO> toRecommentDTOList(List<RecommentEntity> recommentEntities) {
    return recommentEntities.stream()
            .map(this::toRecommentDTO)
            .collect(Collectors.toList());
  }

  // List<RecommentDTO> -> List<RecommentEntity>
  public List<RecommentEntity> toRecommentEntityList(List<RecommentDTO> recommentDTOs, DocumentEntity documentEntity, CommentEntity commentEntity) {
    return recommentDTOs.stream()
            .map(recommentDTO -> toRecommentEntity(recommentDTO, documentEntity, commentEntity))
            .collect(Collectors.toList());
  }
}
