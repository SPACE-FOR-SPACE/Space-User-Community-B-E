package com.example.spacecommunitybackendjwtoauth.user.exceptions;

import com.example.spacecommunitybackendjwtoauth.exception.ErrorCode;
import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;

public class UserNotFoundException extends SpaceCommunityRunTimeException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
