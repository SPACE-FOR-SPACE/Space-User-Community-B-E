package com.example.spacecommunitybackendjwtoauth.community.like.domain;

import com.example.spacecommunitybackendjwtoauth.community.doc.domain.DocumentEntity;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SPACE_LIKE")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private DocumentEntity document;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public LikeEntity(DocumentEntity document, UserEntity user) {
        this.document = document;
        this.user = user;
    }
}
