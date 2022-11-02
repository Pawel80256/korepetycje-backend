package com.example.korepetycjebackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.korepetycjebackend.config.security.MyUserDetails;
import com.example.korepetycjebackend.constants.TempJwtSecret;
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

    private final Date EXPIRATION_TIME = new Date(System.currentTimeMillis() + 1000 * 60 * 60);

    public String generateToken(MyUserDetails myUserDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put("roles", myUserDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()));

        return JWT.create()
                .withSubject(myUserDetails.getUserId().toString())
                .withClaim("roles",myUserDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(EXPIRATION_TIME)
                .sign(Algorithm.HMAC256(TempJwtSecret.TEMP_SECRET.toString().getBytes()));

    }


}
