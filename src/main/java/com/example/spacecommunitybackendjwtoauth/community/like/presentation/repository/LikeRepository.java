package com.example.spacecommunitybackendjwtoauth.community.like.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.community.doc.domain.DocumentEntity;
import com.example.spacecommunitybackendjwtoauth.community.like.domain.LikeEntity;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    void deleteByDocumentAndUser(DocumentEntity document, UserEntity user);

    boolean existsByDocumentAndUser(DocumentEntity documentEntity, UserEntity userEntity);
}