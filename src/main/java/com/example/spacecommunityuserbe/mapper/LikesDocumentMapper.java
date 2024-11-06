package com.example.spacecommunityuserbe.mapper;

import com.example.spacecommunityuserbe.dto.LikesDocumentDTO;
import com.example.spacecommunityuserbe.entity.LikesDocumentEntity;
import org.springframework.stereotype.Component;

@Component
public class LikesDocumentMapper {
  public LikesDocumentEntity toLikesDocumentEntity(LikesDocumentDTO likesDocumentDTO) {
    return new LikesDocumentEntity(likesDocumentDTO.id(), likesDocumentDTO.userId(), likesDocumentDTO.documentId());
  }
  public LikesDocumentDTO toLikesDocumentDTO(LikesDocumentEntity likesDocumentEntity) {
    return new LikesDocumentDTO(likesDocumentEntity.getId(), likesDocumentEntity.getUserId(), likesDocumentEntity.getDocumentId());
  }
}
