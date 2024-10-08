package com.example.spacecommunityuserbe.mapper;

import com.example.spacecommunityuserbe.dto.UserDTO;
import com.example.spacecommunityuserbe.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity(UserDTO userDTO);
    UserDTO toUserDTO(UserEntity userEntity);
}
