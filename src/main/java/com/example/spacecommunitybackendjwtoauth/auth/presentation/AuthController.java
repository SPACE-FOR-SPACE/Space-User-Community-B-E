package com.example.spacecommunitybackendjwtoauth.auth.presentation;

import com.example.spacecommunitybackendjwtoauth.auth.service.implementation.UserJoinService;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.JoinUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserJoinService userJoinService;

    @PostMapping("/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void joinProcess(@RequestBody JoinUserRequest joinUserRequest) {
        userJoinService.joinProcess(joinUserRequest);
    }
}
