package com.example.spacecommunitybackendjwtoauth.community.doc.domain;

import com.example.spacecommunitybackendjwtoauth.community.comment.domain.CommentEntity;
import com.example.spacecommunitybackendjwtoauth.community.like.domain.LikeEntity;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SPACE_DOCUMENT")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Long likes = 0L;

    @Column(nullable = false)
    private Long icon;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeEntity> like;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public DocumentEntity(UserEntity author, String title, String content, String category, Long icon) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.category = category;
        this.likes = 0L;
        this.icon = icon;
    }

    @Builder(builderMethodName = "updateDocument")
    public DocumentEntity(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
