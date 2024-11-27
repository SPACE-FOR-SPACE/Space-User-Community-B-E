package com.example.spacecommunitybackendjwtoauth.user.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// User Repository
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByEmail (String email);

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("""
        update UserEntity u set
        u.username = COALESCE(:username, u.username),
        u.password = COALESCE(:password, u.password),
        u.profile = COALESCE(:profile, u.profile),
        u.introduce = COALESCE(:introduce, u.introduce)
        where u.id = :userId
    """)
    void updateUser(Long userId, String username, String password, String profile, String introduce);

    List<UserEntity> findAllByUsernameContainingOrderByCreatedAtDesc(String username);
}
