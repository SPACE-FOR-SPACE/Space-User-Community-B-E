package com.example.spacecommunitybackendjwtoauth.community.search.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.search.presentation.dto.UserDTO;
import com.example.spacecommunitybackendjwtoauth.community.search.service.SearchUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class UserSearchController {

    private final SearchUserService userSearchService;

    @GetMapping("/user/{search}")
    public List<UserDTO> getUsers(@PathVariable String search) {
        if(search==null) throw new IllegalArgumentException();
        return userSearchService.searchUsers(search);
    }
}
