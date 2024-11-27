package com.example.spacecommunitybackendjwtoauth.community.search.service;

import com.example.spacecommunitybackendjwtoauth.community.search.presentation.dto.UserDTO;

import java.util.List;

public interface SearchUserService {
    List<UserDTO> searchUsers(String query);
}
