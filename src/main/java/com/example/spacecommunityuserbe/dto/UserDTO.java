package com.example.spacecommunityuserbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long USER_ID;
    private String USER_NAME;
    private String USER_PASSWORD;
    private Long USER_AGE;
    private String USER_PROFILE;
}
