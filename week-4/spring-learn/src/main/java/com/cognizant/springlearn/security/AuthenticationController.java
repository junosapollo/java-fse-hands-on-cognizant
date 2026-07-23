package com.cognizant.springlearn.security;

import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    private final JwtService jwt;
    public AuthenticationController(JwtService jwt) { this.jwt=jwt; }
    /** Authenticate with HTTP Basic and exchange the authenticated principal for a bearer token. */
    @GetMapping("/authenticate") public Map<String,String> authenticate(Authentication authentication) { return Map.of("token", jwt.issue(authentication.getName())); }
}
