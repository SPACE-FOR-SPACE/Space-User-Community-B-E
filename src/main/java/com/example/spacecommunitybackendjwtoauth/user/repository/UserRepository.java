package com.example.spacecommunitybackendjwtoauth.user.repository;

import com.example.spacecommunitybackendjwtoauth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByEmail (String email);
    User findUserByEmail (String email);
}
