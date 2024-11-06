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
@Table(name = "SPACE_COMMENTS")
public class CommentsEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @ManyToOne
  @JoinColumn(name = "documentId")
  private DocumentEntity document;

  @Column(nullable = false)
  private String content;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createdAt;
}
