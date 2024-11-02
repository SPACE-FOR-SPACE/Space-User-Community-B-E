package com.example.spacecommunitybackendjwtoauth.user.exceptions;

import com.example.spacecommunitybackendjwtoauth.exception.ErrorCode;
import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;

public class UserExistedException extends SpaceCommunityRunTimeException {
    public UserExistedException() {
        super(ErrorCode.USER_EXISTED);
    }
}