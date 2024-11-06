package com.example.spacecommunityuserbe.config;

import com.example.spacecommunityuserbe.mapper.CommentMapper;
import com.example.spacecommunityuserbe.mapper.DocumentMapper;
import com.example.spacecommunityuserbe.mapper.LikesDocumentMapper;
import com.example.spacecommunityuserbe.mapper.RecommentMapper;
import com.example.spacecommunityuserbe.repository.CommentRepository;
import com.example.spacecommunityuserbe.repository.DocumentRepository;
import com.example.spacecommunityuserbe.repository.LikesDocumentRepository;
import com.example.spacecommunityuserbe.repository.RecommentRepository;
import com.example.spacecommunityuserbe.service.CommentService;
import com.example.spacecommunityuserbe.service.DocumentService;
import com.example.spacecommunityuserbe.service.RecommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
  private final DocumentRepository documentRepository;
  private final DocumentMapper documentMapper;
  private final LikesDocumentRepository likesDocumentRepository;
  private final LikesDocumentMapper likesDocumentMapper;
  private final CommentRepository commentRepository;
  private final CommentMapper commentMapper;
  private final RecommentRepository recommentRepository;
  private final RecommentMapper recommentMapper;

  @Autowired
  public SpringConfiguration(
          DocumentRepository documentRepository,
          DocumentMapper documentMapper,
          LikesDocumentRepository likesDocumentRepository,
          LikesDocumentMapper likesDocumentMapper,
          CommentRepository commentRepository,
          CommentMapper commentMapper,
          RecommentRepository recommentRepository,
          RecommentMapper recommentMapper
  ) {
    this.documentRepository = documentRepository;
    this.documentMapper = documentMapper;
    this.likesDocumentRepository = likesDocumentRepository;
    this.likesDocumentMapper = likesDocumentMapper;
    this.commentRepository = commentRepository;
    this.commentMapper = commentMapper;
    this.recommentRepository = recommentRepository;
    this.recommentMapper = recommentMapper;
  }

  @Bean
  public DocumentService documentService() {
    return new DocumentService(documentRepository, documentMapper, likesDocumentRepository, likesDocumentMapper);
  }

  @Bean
  public CommentService commentService() {
    return new CommentService(commentRepository, documentRepository, commentMapper);
  }

  @Bean
  public RecommentService recommentService() {
    return new RecommentService(recommentRepository, commentRepository, documentRepository, recommentMapper);
  }
}
