package com.example.spacecommunitybackendjwtoauth.auth.service;

import com.example.spacecommunitybackendjwtoauth.user.User;
import com.example.spacecommunitybackendjwtoauth.auth.service.dto.CustomUserDetails;
import com.example.spacecommunitybackendjwtoauth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            return new CustomUserDetails(user);
        }
        return null;
    }
}
