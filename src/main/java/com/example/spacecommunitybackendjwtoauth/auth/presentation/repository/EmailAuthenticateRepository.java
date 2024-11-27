package com.example.spacecommunitybackendjwtoauth.auth.presentation.repository;

import com.example.spacecommunitybackendjwtoauth.auth.domain.EmailAuthenticateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Redis EmailAuthenticate Storage
@Repository
public interface EmailAuthenticateRepository extends CrudRepository<EmailAuthenticateEntity, String> { }