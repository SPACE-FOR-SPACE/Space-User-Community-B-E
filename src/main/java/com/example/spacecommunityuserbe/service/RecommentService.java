package com.example.spacecommunityuserbe.service;

import com.example.spacecommunityuserbe.controller.BaseApiResponse;
import com.example.spacecommunityuserbe.dto.RecommentDTO;
import com.example.spacecommunityuserbe.entity.CommentEntity;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import com.example.spacecommunityuserbe.mapper.RecommentMapper;
import com.example.spacecommunityuserbe.repository.CommentRepository;
import com.example.spacecommunityuserbe.repository.DocumentRepository;
import com.example.spacecommunityuserbe.repository.RecommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecommentService {
  private final RecommentRepository recommentRepository;
  private final CommentRepository commentRepository;
  private final DocumentRepository documentRepository;
  private final RecommentMapper recommentMapper;

  @Autowired
  public RecommentService(
          RecommentRepository recommentRepository,
          CommentRepository commentRepository,
          DocumentRepository documentRepository,
          RecommentMapper recommentMapper
  ) {
    this.recommentRepository = recommentRepository;
    this.commentRepository = commentRepository;
    this.documentRepository = documentRepository;
    this.recommentMapper = recommentMapper;
  }

  // Create Recomment
  @Transactional
  public BaseApiResponse create(RecommentDTO recommentDTO, Long documentId, Long commentId) {
    try {
      DocumentEntity document = documentRepository.findById(documentId).orElse(null);
      CommentEntity comment = commentRepository.findById(commentId).orElse(null);
      if (document == null) {
        return new BaseApiResponse(404, "Document not found");
      }
      if (comment == null) {
        return new BaseApiResponse(404, "Comment not found");
      }
      recommentRepository.save(recommentMapper.toRecommentEntity(recommentDTO, document, comment));
      return new BaseApiResponse(200, "Successfully created");
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }

  // Read Recomment
  @Transactional
  public BaseApiResponse readRecomment(Long documentId, Long commentId) {
    try {
      List<RecommentDTO> recomments = recommentRepository.findAllByDocumentIdAndCommentId(documentId, commentId);
      if(recomments == null) {
        return new BaseApiResponse(404, "Comment Not Found");
      }
      return new BaseApiResponse<>(200, "Successfully Read", recomments);
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }

  // Update Recomment
  @Transactional
  public BaseApiResponse updateRecomment(RecommentDTO recommentDTO, Long documentId, Long commentId, Long recommentId) {
    try {
      RecommentDTO recomment = recommentRepository.findByDocumentIdAndCommentIdAndId(documentId, commentId, recommentId);
      if(recomment == null) {
        return new BaseApiResponse(404, "Recomment Not Found");
      }
      // 본인이 작성한 대댓글만 수정할 수 있도록 수정 필요
      RecommentDTO updated_recomment = new RecommentDTO(commentId, recomment.userId(), recomment.commentId(), recommentDTO.content(), recomment.createdAt());
      DocumentEntity document = documentRepository.findById(documentId).orElse(null);
      CommentEntity comment = commentRepository.findById(commentId).orElse(null);
      if(document == null) {
        return new BaseApiResponse(404, "Document not found");
      }
      if(comment == null) {
        return new BaseApiResponse(404, "Comment not found");
      }
      recommentRepository.save(recommentMapper.toRecommentEntity(updated_recomment, document, comment));
      return new BaseApiResponse(200, "Successfully Updated");
    } catch (Exception e) {
      return new BaseApiResponse(500, "Error Occurred");
    }
  }

  // Delete Recomment
  @Transactional
  public BaseApiResponse deleteRecomment(Long documentId, Long commentId, Long recommentId) {
    try {
      // 본인이 작성한 대댓글만 삭제할 수 있도록 수정 필요
      recommentRepository.deleteByDocumentIdAndCommentIdAndId(commentId, documentId, recommentId);
      return new BaseApiResponse(200, "Successfully Deleted");
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }
}
