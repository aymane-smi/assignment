package com.hahn.assignmenet.Services.Utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JwtService {
    @Value("${jwt.secret_key}")
    private String secret;
    private final long EXPIRATION_TIME = 3600 * 60;
    public String generateToken(UserDetails userDetails){
        return JWT.create()
            .withSubject(userDetails.getUsername())
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(Algorithm.HMAC256(secret));
    }

    public String extractEmail(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        try{
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                                         .withSubject(userDetails.getUsername())
                                         .build();
            jwtVerifier.verify(token);
            return !isTokenExpired(token);
        }catch(JWTVerificationException | IllegalArgumentException e){
            return false;
        }
    }

    private Boolean isTokenExpired(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getExpiresAt().before(new Date());
    }
}
