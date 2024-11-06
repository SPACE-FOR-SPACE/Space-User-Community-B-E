package com.example.spacecommunityuserbe.service;

import com.example.spacecommunityuserbe.controller.BaseApiResponse;
import com.example.spacecommunityuserbe.dto.CommentDTO;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import com.example.spacecommunityuserbe.mapper.CommentMapper;
import com.example.spacecommunityuserbe.repository.CommentRepository;
import com.example.spacecommunityuserbe.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
  private final CommentRepository commentRepository;
  private final DocumentRepository documentRepository;
  private final CommentMapper commentMapper;

  @Autowired
  public CommentService(CommentRepository commentRepository, DocumentRepository documentRepository, CommentMapper commentMapper) {
    this.commentRepository = commentRepository;
    this.documentRepository = documentRepository;
    this.commentMapper = commentMapper;
  }

  // Create Comment
  @Transactional
  public BaseApiResponse createComment(CommentDTO commentDTO, Long documentId) {
    try {
      DocumentEntity document = documentRepository.findById(documentId).orElse(null);
      if(document == null) {
        return new BaseApiResponse(404, "Document not found");
      }
      commentRepository.save(commentMapper.toCommentsEntity(commentDTO, document));
      return new BaseApiResponse(200, "Successfully Created");
    } catch (Exception e) {
      return new BaseApiResponse(500, "Error Occurred");
    }
  }


  // Read Comment
  @Transactional
  public BaseApiResponse readComment(Long documentId) {
    try {
      List<CommentDTO> comments = commentRepository.findCommentsDTOByDocumentId(documentId);
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
  public BaseApiResponse updateComment(CommentDTO commentDTO, Long documentId, Long commentId) {
    try {
      CommentDTO comment = commentRepository.findCommentsDTOByIdAndDocumentId(commentId, documentId);
      if(comment == null) {
        return new BaseApiResponse(404, "Comment Not Found");
      }
      // 본인이 작성한 댓글만 수정할 수 있도록 수정 필요
      CommentDTO updated_comment = new CommentDTO(commentId, comment.userId(), comment.documentId(), commentDTO.content(), comment.createdAt());
      DocumentEntity document = documentRepository.findById(comment.documentId()).orElse(null);
      if(document == null) {
        return new BaseApiResponse(404, "Document not found");
      }
      commentRepository.save(commentMapper.toCommentsEntity(updated_comment, document));
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
      commentRepository.deleteByIdAndDocumentId(commentId, documentId);
      return new BaseApiResponse(200, "Successfully Deleted");
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }
}
