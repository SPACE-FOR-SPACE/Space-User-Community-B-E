package com.example.spacecommunityuserbe.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SPACE_COMMENT")
public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @ManyToOne
  @JoinColumn(name = "document_id")
  private DocumentEntity document;

  @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
  private List<RecommentEntity> recomment;

  @Column(nullable = false)
  private String content;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createdAt;
}
