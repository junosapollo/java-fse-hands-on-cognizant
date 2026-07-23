package com.cognizant.springlearn.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final SecretKey key;
    public JwtService(@Value("${security.jwt.secret:course-handson-secret-key-change-me-32chars}") String secret) {
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    public String issue(String username) {
        Date now = new Date();
        return Jwts.builder().subject(username).issuedAt(now).expiration(new Date(now.getTime()+Duration.ofHours(1).toMillis())).signWith(key).compact();
    }
    public String subject(String token) { return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject(); }
}
