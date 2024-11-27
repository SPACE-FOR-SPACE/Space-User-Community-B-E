package com.example.spacecommunitybackendjwtoauth.community.recomment.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.community.recomment.domain.RecommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RecommentRepository extends JpaRepository<RecommentEntity, Long> {
    @Query("select r from RecommentEntity r where r.comment.id = :commentId")
    List<RecommentEntity> findAllComments(Long commentId);

    @Query("select count(r) from RecommentEntity r where r.comment.id = :commentId")
    Long countAllRecomments(Long commentId);

    @Transactional
    @Modifying
    @Query("update RecommentEntity set content = :content where id = :id")
    void updateRecomment(Long id, String content);
}