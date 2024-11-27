package com.example.spacecommunitybackendjwtoauth.community.search.service.impl;

import com.example.spacecommunitybackendjwtoauth.admin.ban.presentation.repository.BanRepository;
import com.example.spacecommunitybackendjwtoauth.community.search.presentation.dto.UserDTO;
import com.example.spacecommunitybackendjwtoauth.community.search.service.SearchUserService;
import com.example.spacecommunitybackendjwtoauth.user.domain.UserEntity;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchUserServiceImpl implements SearchUserService {

    private final UserRepository userRepository;
    private final BanRepository banRepository;

    @Override
    public List<UserDTO> searchUsers(String query) {
        List<UserEntity> userList = userRepository.findAllByUsernameContainingOrderByCreatedAtDesc(query);
        return userList.stream().filter(u -> !banRepository.existsByUser(u)).map(u -> new UserDTO(u.getId(), u.getUsername(), u.getProfile(), u.getCreatedAt().toString())).toList();
    }
}
