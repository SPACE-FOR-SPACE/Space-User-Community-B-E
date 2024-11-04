package com.example.spacecommunitybackendjwtoauth.auth.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.JWTUserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<JWTUserDTO, String> { }