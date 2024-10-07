package com.example.spacecommunityuserbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long USER_ID;

    @Column(nullable = false)
    private String USER_NAME;

    @Column(nullable = false)
    private String USER_PASSWORD;

    @Column(nullable = false)
    private Long USER_AGE;

    @Column
    private String USER_PROFILE;
}
