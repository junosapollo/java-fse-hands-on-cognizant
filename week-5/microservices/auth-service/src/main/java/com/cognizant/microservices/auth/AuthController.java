package com.cognizant.microservices.auth;
import io.jsonwebtoken.Jwts;
import java.time.Duration;
import java.util.*;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController public class AuthController { private final SecretKey key; public AuthController(SecretKey key){this.key=key;}
 @GetMapping("/token") public Map<String,String> token(Authentication a){Date now=new Date(); String token=Jwts.builder().subject(a.getName()).issuedAt(now).expiration(new Date(now.getTime()+Duration.ofHours(1).toMillis())).signWith(key).compact(); return Map.of("token",token);}
}
