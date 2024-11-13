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

import java.util.List;

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
    System.out.println(documentDTO.toString());
    System.out.println(documentMapper.toDocumentEntity(documentDTO).toString());
    try {
      documentRepository.save(documentMapper.toDocumentEntity(documentDTO));
      return new BaseApiResponse(201, "Successfully created");
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }

  // Document Read
  @Transactional
  public BaseApiResponse readDocument(Long id) {
    try {
      DocumentEntity documentEntity = documentRepository.findById(id).orElse(null);
      DocumentDTO document = documentMapper.toDocumentDTO(documentEntity);
      System.out.println(documentEntity.getComments());
      if (document == null) {
        return new BaseApiResponse(404, "Document not found");
      }
      return new BaseApiResponse<>(200, "Successfully read", document);
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }

  // Document Update
  @Transactional
  public BaseApiResponse updateDocument(Long id, DocumentDTO documentDTO) {
    try {
      if(documentRepository.existsById(id)) {
        DocumentEntity document = documentRepository.findById(id).orElse(null);
        DocumentDTO updatedDocument = new DocumentDTO(document.getId(), document.getUserId(), documentDTO.comments(), documentDTO.title(), documentDTO.content(), documentDTO.icon(), documentDTO.category(), document.getLikes(), document.getCreatedAt());
        documentRepository.save(documentMapper.toDocumentEntity(updatedDocument));
        return new BaseApiResponse(200, "Successfully updated");
      } else {
        return new BaseApiResponse(404, "Document not found");
      }
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }

  // Document Delete
  @Transactional
  public BaseApiResponse deleteDocument(Long id) {
    try {
      DocumentEntity documentEntity = documentRepository.findById(id).orElse(null);
      if(documentEntity != null) {
        documentRepository.deleteById(id);
        return new BaseApiResponse(200, "Successfully deleted");
      } else {
        return new BaseApiResponse(404, "Document not found");
      }
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
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
        DocumentDTO updatedDocument = new DocumentDTO(document.id(), document.userId(), document.comments(), document.title(), document.content(), document.icon(), document.category(), document.likes() - 1, document.createdAt());
        documentRepository.save(documentMapper.toDocumentEntity(updatedDocument));
      }
      else {
        DocumentDTO document = documentRepository.findDocumentDTOById(likesDocumentDTO.documentId());
        DocumentDTO updatedDocument = new DocumentDTO(document.id(), document.userId(), document.comments(), document.title(), document.content(), document.icon(), document.category(), document.likes() + 1, document.createdAt());
        documentRepository.save(documentMapper.toDocumentEntity(updatedDocument));
        likesDocumentRepository.save(likesDocumentMapper.toLikesDocumentEntity(likesDocumentDTO));
      }
      return new BaseApiResponse(200, "Successfully added/removed likes");
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }

  // Document Search
  @Transactional
  public BaseApiResponse searchDocument(String title) {
    try {
      List<DocumentDTO> documentDTOList = documentMapper.toDocumentDTOList(documentRepository.findAllByTitleContaining(title));
      return new BaseApiResponse(200, "Successfully searched", documentDTOList);
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }

  // Document List
  public BaseApiResponse getDocumentList(String orderBy) {
    try {
      List<DocumentDTO> documentDTOList;
      if(orderBy == "createdAt") {
        documentDTOList = documentMapper.toDocumentDTOList(documentRepository.findAllByOrderByCreatedAtAsc());
      } else {
        documentDTOList = documentMapper.toDocumentDTOList(documentRepository.findAllByOrderByLikesDesc());
      }
      return new BaseApiResponse(200, "Successfully got", documentDTOList);
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }

  // Document List By UserId
  public BaseApiResponse getDocumentListByUserId(Long userId) {
    try {
      List<DocumentDTO> documentDTOList = documentMapper.toDocumentDTOList(documentRepository.findAllByUserIdOrderByCreatedAtAsc(userId));
      return new BaseApiResponse(200, "Successfully got", documentDTOList);
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }

}
