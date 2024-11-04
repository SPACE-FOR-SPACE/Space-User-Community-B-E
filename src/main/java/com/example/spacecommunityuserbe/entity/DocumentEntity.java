package com.example.spacecommunityuserbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SPACE_DOCUMENT")
public class DocumentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn(name = "userId")
  private Long userId;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private DocumentIcon icon;

  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private Long likes = 0L;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createdAt;
}
