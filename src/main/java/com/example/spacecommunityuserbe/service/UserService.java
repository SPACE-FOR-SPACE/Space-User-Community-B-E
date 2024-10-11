package com.example.spacecommunityuserbe.service;

import com.example.spacecommunityuserbe.dto.UserDTO;
import com.example.spacecommunityuserbe.entity.UserEntity;
import com.example.spacecommunityuserbe.exception.CustomException;
import com.example.spacecommunityuserbe.exception.ErrorCode;
import com.example.spacecommunityuserbe.mapper.UserMapper;
import com.example.spacecommunityuserbe.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Boolean userAuthenticate(UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByName(userDTO.getName());
        return userEntityOptional.filter(userEntity -> passwordEncoder.matches(userDTO.getPassword(), userEntity.getPassword())).isPresent();
    }

    @Transactional
    public void createUser(UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByName(userDTO.getName());
        if (userEntityOptional.isPresent()) throw new CustomException(ErrorCode.USER_EXISTS);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        saveUser(userDTO);
    }

    private void saveUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.toUserEntity(userDTO);
        userMapper.toUserDTO(userRepository.save(userEntity));
    }

    @Transactional
    public void deleteUser(UserDTO userDTO) {
        if(!userAuthenticate(userDTO)) throw new CustomException(ErrorCode.USER_NOT_AUTHENTICATED);
        userRepository.delete(userMapper.toUserEntity(userDTO));
    }
}