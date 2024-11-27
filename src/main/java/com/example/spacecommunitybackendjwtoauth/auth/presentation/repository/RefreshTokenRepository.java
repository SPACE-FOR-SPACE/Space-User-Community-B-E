package com.example.spacecommunitybackendjwtoauth.auth.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.auth.domain.JWTUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// Redis RefreshToken Storage
@Repository
public interface RefreshTokenRepository extends CrudRepository<JWTUserEntity, String> {
    @Transactional
    void deleteByEmail(String email);
}