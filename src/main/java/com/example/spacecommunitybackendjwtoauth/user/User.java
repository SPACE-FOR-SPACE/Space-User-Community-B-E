package com.example.spacecommunitybackendjwtoauth.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// User Entity
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String profile;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder(builderMethodName = "UserRegisterBuilder")
    public User(String username, String email, String password, Integer age, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.role = role;
        this.profile = null;
    }

    @Builder(builderMethodName = "UserUpdateBuilder")
    public User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }
}
