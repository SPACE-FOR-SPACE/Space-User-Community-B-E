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
@Table(name = "SPACE_RECOMMENT")
public class RecommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 나중에 foreign key 로 바꿀것
  @Column
  private Long userId;

  @ManyToOne
  @JoinColumn(name = "document_id")
  private DocumentEntity document;

  @ManyToOne
  @JoinColumn(name = "comment_id", referencedColumnName = "id")
  private CommentEntity comment;

  @Column
  private String content;

  @CreationTimestamp
  @Column
  private LocalDateTime createdAt;
}
