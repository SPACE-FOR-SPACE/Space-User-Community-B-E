package com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.admin.ban.domain.BanEntity;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BanRepository extends JpaRepository<BanEntity, Long> {
    boolean existsByUser(UserEntity userEntity);
    @Transactional
    void deleteByUserUsername(String username);
}