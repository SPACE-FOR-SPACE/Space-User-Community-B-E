package com.example.spacecommunitybackendjwtoauth.community.comment.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.community.comment.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("select c from CommentEntity c where c.document.id = :docId")
    List<CommentEntity> findAllComments(Long docId);

    @Transactional
    @Modifying
    @Query("update CommentEntity set content = :content where id = :id")
    void updateComment(Long id, String content);
}