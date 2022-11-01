package com.example.korepetycjebackend.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtUtil {
    private final String TEMP_SECRET = "secret";
    private final Date EXPIRATION_TIME = new Date(System.currentTimeMillis() + 1000 * 60 * 60);

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(EXPIRATION_TIME)
                .signWith(SignatureAlgorithm.HS256, TEMP_SECRET)
                .compact();
    }


}
