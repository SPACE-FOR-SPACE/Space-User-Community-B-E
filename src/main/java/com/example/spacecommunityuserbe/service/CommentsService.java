package com.example.spacecommunityuserbe.service;

import com.example.spacecommunityuserbe.controller.BaseApiResponse;
import com.example.spacecommunityuserbe.dto.CommentsDTO;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import com.example.spacecommunityuserbe.mapper.CommentsMapper;
import com.example.spacecommunityuserbe.repository.CommentsRepository;
import com.example.spacecommunityuserbe.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentsService {
  private final CommentsRepository commentsRepository;
  private final DocumentRepository documentRepository;
  private final CommentsMapper commentsMapper;

  public CommentsService(CommentsRepository commentsRepository, DocumentRepository documentRepository, CommentsMapper commentsMapper) {
    this.commentsRepository = commentsRepository;
    this.documentRepository = documentRepository;
    this.commentsMapper = commentsMapper;
  }

  // Create Comment
  @Transactional
  public BaseApiResponse createComment(CommentsDTO commentsDTO, Long documentId) {
    try {
      DocumentEntity document = documentRepository.findById(documentId).orElse(null);
      if(document == null) {
        return new BaseApiResponse(404, "Document not found");
      }
      commentsRepository.save(commentsMapper.toCommentsEntity(commentsDTO, document));
      return new BaseApiResponse(200, "Successfully Created");
    } catch (Exception e) {
      return new BaseApiResponse(500, "Error Occurred");
    }
  }


  // Read Comment
  @Transactional
  public BaseApiResponse readComment(Long documentId) {
    try {
      List<CommentsDTO> comments = commentsRepository.findCommentsDTOByDocumentId(documentId);
      if(comments == null) {
        return new BaseApiResponse(404, "Comment Not Found");
      }
      return new BaseApiResponse<>(200, "Successfully Read", comments);
    } catch (Exception e) {
      return new BaseApiResponse(500, "Error Occurred");
    }
  }


  // Update Comment
  @Transactional
  public BaseApiResponse updateComment(CommentsDTO commentsDTO, Long documentId, Long commentId) {
    try {
      CommentsDTO comment = commentsRepository.findCommentsDTOByIdAndDocumentId(commentId, documentId);
      if(comment == null) {
        return new BaseApiResponse(404, "Comment Not Found");
      }
      // 본인이 작성한 댓글만 수정할 수 있도록 수정 필요
      CommentsDTO updated_comment = new CommentsDTO(commentId, comment.userId(), comment.documentId(), commentsDTO.content(), comment.createdAt());
      DocumentEntity document = documentRepository.findById(comment.documentId()).orElse(null);
      if(document == null) {
        return new BaseApiResponse(404, "Document not found");
      }
      commentsRepository.save(commentsMapper.toCommentsEntity(updated_comment, document));
      return new BaseApiResponse(200, "Successfully Updated");
    } catch (Exception e) {
      return new BaseApiResponse(500, "Error Occurred");
    }
  }


  // Delete Comment
  @Transactional
  public BaseApiResponse deleteComment(Long documentId, Long commentId) {
    try {
      // 본인이 작성한 댓글만 삭제할 수 있도록 수정 필요
      commentsRepository.deleteByIdAndDocumentId(commentId, documentId);
      return new BaseApiResponse(200, "Successfully Deleted");
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }
}
