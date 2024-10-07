package com.example.spacecommunityuserbe.service;

import com.example.spacecommunityuserbe.dto.UserDTO;
import com.example.spacecommunityuserbe.entity.UserEntity;
import com.example.spacecommunityuserbe.mapper.UserMapper;
import com.example.spacecommunityuserbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

}
