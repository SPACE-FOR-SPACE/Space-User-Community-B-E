package com.example.spacecommunityuserbe.controller;

import com.example.spacecommunityuserbe.dto.UserDTO;
import com.example.spacecommunityuserbe.service.SessionService;
import com.example.spacecommunityuserbe.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.status(201).body("User registered");
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest) {
        if (!userService.userAuthenticate(userDTO)) return ResponseEntity.status(401).body("User Data does not match");
        HttpSession session = httpServletRequest.getSession();
        sessionService.saveSession(session.getId(), userDTO.getName());
        return ResponseEntity.status(200).body("User logged in");
    }

//
//    @GetMapping(value = "/logout")
//
//    @GetMapping(value = "/profile/{user}")


}
