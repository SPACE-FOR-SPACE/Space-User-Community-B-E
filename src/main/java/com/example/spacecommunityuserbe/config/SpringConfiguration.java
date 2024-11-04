package com.example.spacecommunityuserbe.config;

import com.example.spacecommunityuserbe.mapper.DocumentMapper;
import com.example.spacecommunityuserbe.repository.DocumentRepository;
import com.example.spacecommunityuserbe.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
  private final DocumentRepository documentRepository;
  private final DocumentMapper documentMapper;

  @Autowired
  public SpringConfiguration(DocumentRepository documentRepository, DocumentMapper documentMapper) {
    this.documentRepository = documentRepository;
    this.documentMapper = documentMapper;
  }

  @Bean
  public DocumentService documentService() {
    return new DocumentService(documentRepository, documentMapper);
  }
}
