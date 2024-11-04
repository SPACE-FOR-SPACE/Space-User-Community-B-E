package com.example.spacecommunityuserbe.controller;

import com.example.spacecommunityuserbe.dto.DocumentDTO;
import com.example.spacecommunityuserbe.repository.DocumentRepository;
import com.example.spacecommunityuserbe.service.DocumentService;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/community")
public class DocumentController {
  private final DocumentService documentService;
  private final DocumentRepository documentRepository;

  public DocumentController(DocumentService documentService, DocumentRepository documentRepository) {
    this.documentService = documentService;
    this.documentRepository = documentRepository;
  }

  @PostMapping("/community/document")
  public BaseApiResponse create(@RequestBody DocumentDTO document) {
    return documentService.createDocument(document);
  }

  @PutMapping("/community/documents/{id}")
  public BaseApiResponse update(@PathVariable Long id, @RequestBody UpdateDocumentRequest updateDocumentRequest) {
    return documentService.updateDocument(id, updateDocumentRequest);
  }

  @DeleteMapping("/community/documents/{id}")
  public BaseApiResponse delete(@PathVariable Long id) {
    return documentService.deleteDocument(id);
  }

  @GetMapping("/community/documents/{id}")
  public Object read(@PathVariable Long id) {
    return documentService.readDocument(id);
  }
}
