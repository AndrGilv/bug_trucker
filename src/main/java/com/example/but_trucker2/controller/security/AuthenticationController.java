package com.example.but_trucker2.controller.security;

import com.example.but_trucker2.controller.request.AuthenticationRequest;
import com.example.but_trucker2.repository.UserRepo;
import com.example.but_trucker2.springSecurity.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepo users;

    @PostMapping("/signin")
            public ResponseEntity<Map<Object, Object>> signIn(@RequestBody AuthenticationRequest data) {
                try {
                    String username = data.getUsername();
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
                    String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
                    Map<Object, Object> model = new HashMap<>();
                    model.put("username", username);
                    model.put("token", token);
                    return ResponseEntity.ok(model);
                } catch (AuthenticationException e) {
                    throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
