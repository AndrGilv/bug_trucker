package com.example.but_trucker2.service.security;

import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo repository;

    public CustomUserDetailsService(UserRepo repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username = " + username + " not found"));
}
}