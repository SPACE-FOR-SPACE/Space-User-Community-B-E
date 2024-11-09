package com.example.spacecommunitybackendjwtoauth.user.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// User Repository
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail (String email);
    User findUserByEmail (String email);
    @Transactional
    @Modifying
    @Query("update User set username = :username, password = :password, profile = :profile where id = :userId")
    void updateUserByEmail(Long userId, String username, String password, String profile);

    boolean existsByUsername(String username);
}
