package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.dto.request.AuthenticationRequest;
import com.example.korepetycjebackend.utils.JwtUtil;
import com.example.korepetycjebackend.config.security.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/api/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
     try {
         authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(authenticationRequest.getEmailAddress(), authenticationRequest.getPassword())
         );
     } catch (BadCredentialsException e) {
         throw new RuntimeException("bad credentials");
     }
     var userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmailAddress());
     var jwt = jwtUtil.generateToken(userDetails);
     return ResponseEntity.ok(jwt);
    }
}
