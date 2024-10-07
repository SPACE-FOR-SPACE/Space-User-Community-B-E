package com.example.spacecommunityuserbe.controller;

import com.example.spacecommunityuserbe.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {

    }

//    @PostMapping(value = "/login")
//
//    @GetMapping(value = "/logout")
//
//    @GetMapping(value = "/profile/{user}")


}
