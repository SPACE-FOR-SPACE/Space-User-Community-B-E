package com.example.spacecommunityuserbe.service;

import com.example.spacecommunityuserbe.controller.BaseApiResponse;
import com.example.spacecommunityuserbe.controller.UpdateDocumentRequest;
import com.example.spacecommunityuserbe.dto.DocumentDTO;
import com.example.spacecommunityuserbe.entity.DocumentEntity;
import com.example.spacecommunityuserbe.mapper.DocumentMapper;
import com.example.spacecommunityuserbe.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentService {
  private final DocumentRepository documentRepository;
  private final DocumentMapper documentMapper;

  @Autowired
  public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
    this.documentRepository = documentRepository;
    this.documentMapper = documentMapper;
  }

  @Transactional
  public Object readDocument(Long id) {
    try {
      DocumentDTO document = documentRepository.findDocumentDTOById(id);
      Map<String, Object> response = new HashMap<>();
      response.put("status", 200);
      Map<String, String> documents = new HashMap<>();
      documents.put("title", document.getTitle());
      documents.put("content", document.getContent());
      response.put("documents", documents);
      return response;
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }
  @Transactional
  public BaseApiResponse createDocument(DocumentDTO documentDTO) {
    try {
      DocumentEntity documentEntity = documentMapper.toDocumentEntity(documentDTO);
      documentMapper.toDocumentDTO(documentRepository.save(documentEntity));
      return new BaseApiResponse(200, "Successfully created");
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }

  @Transactional
  public BaseApiResponse deleteDocument(Long id) {
    try {
      documentRepository.deleteById(id);
      return new BaseApiResponse(200, "Successfully deleted");
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }

  @Transactional
  public BaseApiResponse updateDocument(Long id, UpdateDocumentRequest updateDocumentRequest) {
    try {
      DocumentDTO document = documentRepository.findDocumentDTOById(id);
      document.setTitle(updateDocumentRequest.title());
      document.setContent(updateDocumentRequest.content());
      documentRepository.save(documentMapper.toDocumentEntity(document));
      return new BaseApiResponse(200, "Successfully updated");
    } catch (Exception e) {
      return new BaseApiResponse(500, e.getMessage());
    }
  }
}
