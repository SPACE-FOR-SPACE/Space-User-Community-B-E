package com.example.spacecommunitybackendjwtoauth.admin.broadcast.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.admin.broadcast.domain.BroadcastEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BroadcastRepository extends JpaRepository<BroadcastEntity, Long> {
    @Transactional
    @Modifying
    @Query("update BroadcastEntity set title = :title, contents = :contents where id = :id")
    void updateById(String title, String contents, Long id);
}
