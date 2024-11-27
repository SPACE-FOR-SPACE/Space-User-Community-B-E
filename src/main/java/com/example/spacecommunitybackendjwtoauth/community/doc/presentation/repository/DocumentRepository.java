package com.example.spacecommunitybackendjwtoauth.community.doc.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.community.doc.domain.DocumentEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    @Transactional
    @Modifying
    @Query("update DocumentEntity set title = :title, content = :content, category = :category, icon = :icon where id = :id")
    void updateById(Long id, String title, String content, String category, Long icon);

    @Transactional
    @Modifying
    @Query("update DocumentEntity set likes = likes + :amount where id = :id")
    void likeDocument(Long id, Long amount);

    List<DocumentEntity> findAll(Sort sort);

    List<DocumentEntity> findAllByAuthorUsername(Sort sort, String authorUsername);

    List<DocumentEntity> findAllByTitleContainingOrderByCreatedAtDesc(String query);
}