package com.example.spacecommunityuserbe.mapper;

import com.example.spacecommunityuserbe.dto.UserDTO;
import com.example.spacecommunityuserbe.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toUserEntity(UserDTO userDTO) {
        return new UserEntity(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getPassword(),
                userDTO.getAge(),
                userDTO.getProfile()
        );
    }
    public UserDTO toUserDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .password(userEntity.getPassword())
                .age(userEntity.getAge())
                .profile(userEntity.getProfile())
                .build();
    }
}
