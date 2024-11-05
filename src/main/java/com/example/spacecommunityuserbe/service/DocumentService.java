package com.example.spacecommunityuserbe.service;

import com.example.spacecommunityuserbe.controller.BaseApiResponse;
import com.example.spacecommunityuserbe.dto.DocumentDTO;
import com.example.spacecommunityuserbe.dto.LikesDocumentDTO;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import com.example.spacecommunityuserbe.mapper.DocumentMapper;
import com.example.spacecommunityuserbe.mapper.LikesDocumentMapper;
import com.example.spacecommunityuserbe.repository.DocumentRepository;
import com.example.spacecommunityuserbe.repository.LikesDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentService {
  private final DocumentRepository documentRepository;
  private final LikesDocumentRepository likesDocumentRepository;
  private final DocumentMapper documentMapper;
  private final LikesDocumentMapper likesDocumentMapper;

  @Autowired
  public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper, LikesDocumentRepository likesDocumentRepository, LikesDocumentMapper likesDocumentMapper) {
    this.documentRepository = documentRepository;
    this.documentMapper = documentMapper;
    this.likesDocumentRepository = likesDocumentRepository;
    this.likesDocumentMapper = likesDocumentMapper;
  }
  // Document Create
  @Transactional
  public BaseApiResponse createDocument(DocumentDTO documentDTO) {
    try {
      DocumentEntity documentEntity = documentMapper.toDocumentEntity(documentDTO);
      documentMapper.toDocumentDTO(documentRepository.save(documentEntity));
      return new BaseApiResponse(201, "Successfully created");
    } catch (Exception e) {
      return new BaseApiResponse(400, "Invalid input");
    }
  }

  // Document Read
  @Transactional
  public BaseApiResponse readDocument(Long id) {
    try {
      DocumentDTO document = documentRepository.findDocumentDTOById(id);
      return new BaseApiResponse<>(200, "Successfully read", document);
    } catch (Exception e) {
      return new BaseApiResponse(404, "Document not found");
    }
  }

  // Document Read
  @Transactional
  public BaseApiResponse updateDocument(Long id, DocumentDTO documentDTO) {
    try {
      if(documentRepository.existsById(id)) {
        DocumentDTO document = documentRepository.findDocumentDTOById(id);
        DocumentDTO updatedDocument = new DocumentDTO(document.id(), document.userId(), documentDTO.title(), documentDTO.content(), documentDTO.icon(), documentDTO.category(), document.likes(), document.createdAt());
        documentRepository.save(documentMapper.toDocumentEntity(updatedDocument));
        return new BaseApiResponse(200, "Successfully updated");
      } else {
        return new BaseApiResponse(404, "Document not found");
      }
    } catch (Exception e) {
      return new BaseApiResponse(400, "Invalid input");
    }
  }

  // Document Delete
  @Transactional
  public BaseApiResponse deleteDocument(Long id, Long userId) {
    try {
      if(documentRepository.findDocumentDTOById(id).userId().equals(userId)) {
        documentRepository.deleteById(id);
        return new BaseApiResponse(200, "Successfully deleted");
      } else {
        return new BaseApiResponse(404, "Document not found");
      }
    } catch (Exception e) {
      return new BaseApiResponse(403, "Unauthorized access");
    }
  }

  // Document Like
  @Transactional
  public BaseApiResponse like(LikesDocumentDTO likesDocumentDTO) {
    if(!documentRepository.existsById(likesDocumentDTO.documentId())) {
      return new BaseApiResponse(404, "Document not found");
    }
    try {
      if (likesDocumentRepository.existsByDocumentIdAndUserId(likesDocumentDTO.documentId(), likesDocumentDTO.userId())) {
        likesDocumentRepository.deleteByDocumentIdAndUserId(likesDocumentDTO.documentId(), likesDocumentDTO.userId());
        DocumentDTO document = documentRepository.findDocumentDTOById(likesDocumentDTO.documentId());
        DocumentDTO updatedDocument = new DocumentDTO(document.id(), document.userId(), document.title(), document.content(), document.icon(), document.category(), document.likes() - 1, document.createdAt());
        documentRepository.save(documentMapper.toDocumentEntity(updatedDocument));
      }
      else {
        DocumentDTO document = documentRepository.findDocumentDTOById(likesDocumentDTO.documentId());
        DocumentDTO updatedDocument = new DocumentDTO(document.id(), document.userId(), document.title(), document.content(), document.icon(), document.category(), document.likes() + 1, document.createdAt());
        documentRepository.save(documentMapper.toDocumentEntity(updatedDocument));
        likesDocumentRepository.save(likesDocumentMapper.toLikesDocumentEntity(likesDocumentDTO));
      }
      return new BaseApiResponse(200, "Successfully added/removed likes");
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }
}
