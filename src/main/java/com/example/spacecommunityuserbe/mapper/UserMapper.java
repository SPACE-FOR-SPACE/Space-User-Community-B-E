package com.example.spacecommunityuserbe.mapper;

import com.example.spacecommunityuserbe.dto.UserDTO;
import com.example.spacecommunityuserbe.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toUserDTO(UserEntity userEntity) {
        return UserDTO.builder()
            .USER_ID(userEntity.getUSER_ID())
            .USER_NAME(userEntity.getUSER_NAME())
            .USER_PASSWORD(userEntity.getUSER_PASSWORD())
            .USER_AGE(userEntity.getUSER_AGE())
            .USER_PROFILE(userEntity.getUSER_PROFILE())
            .build();
    }

    public UserEntity toUserEntity(UserDTO userDTO) {
        return new UserEntity(
                userDTO.getUSER_ID(),
                userDTO.getUSER_NAME(),
                userDTO.getUSER_PASSWORD(),
                userDTO.getUSER_AGE(),
                userDTO.getUSER_PROFILE()
        );
    }
}
