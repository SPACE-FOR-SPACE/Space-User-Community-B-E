package com.example.spacecommunitybackendjwtoauth.admin.broadcast.domain;

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
@Table(name = "SPACE_BROADCAST")
public class BroadcastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String contents;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public BroadcastEntity(String title, String contents, UserEntity author) {
        this.title = title;
        this.contents = contents;
        this.author = author;
    }
}
