package com.example.spacecommunityuserbe.service;

import com.example.spacecommunityuserbe.dto.UserDTO;
import com.example.spacecommunityuserbe.entity.UserEntity;
import com.example.spacecommunityuserbe.mapper.UserMapper;
import com.example.spacecommunityuserbe.repository.UserRepository;
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

    public Boolean isUserVerify(String username) {
        Optional<UserEntity> userEntityOptional = userRepository.findByName(username);
        return userEntityOptional.isPresent();
    }

    public Boolean userAuthenticate(UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByName(userDTO.getName());
        return userEntityOptional.filter(userEntity -> passwordEncoder.matches(userDTO.getPassword(), userEntity.getPassword())).isPresent();
    }

    public void createUser(UserDTO userDTO) {
        if(isUserVerify(userDTO.getName())) return; // Exception
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        saveUser(userDTO);
    }

    private void saveUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.toUserEntity(userDTO);
        userMapper.toUserDTO(userRepository.save(userEntity));
    }

    public void deleteUser(UserDTO userDTO) {
        if(!userAuthenticate(userDTO)) return; // Exception
        userRepository.delete(userMapper.toUserEntity(userDTO));
    }
}