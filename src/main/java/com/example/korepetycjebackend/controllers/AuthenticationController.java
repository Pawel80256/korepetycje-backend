package com.example.korepetycjebackend.controllers;

import com.example.korepetycjebackend.config.security.MyUserDetails;
import com.example.korepetycjebackend.dto.request.AuthenticationRequest;
import com.example.korepetycjebackend.models.UserData;
import com.example.korepetycjebackend.repositories.UserDataRepository;
import com.example.korepetycjebackend.utils.JwtUtil;
import com.example.korepetycjebackend.config.security.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final UserDataRepository userDataRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
     try {
         authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(authenticationRequest.getEmailAddress(), authenticationRequest.getPassword())
         );
     } catch (BadCredentialsException e) {
         throw new RuntimeException("bad credentials");
     }
     MyUserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmailAddress());
     var jwt = jwtUtil.generateToken(userDetails);
     return ResponseEntity.ok(jwt);
    }

    @GetMapping("/userData/{id}")
    public ResponseEntity<UserData> getUserDataById(@PathVariable UUID id){
        return ResponseEntity.ok(userDataRepository.findById(id).orElseThrow(()-> new RuntimeException("User data not found")));
    }
}
