package com.cognizant.microservices.auth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import javax.crypto.SecretKey;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration public class AuthSecurityConfig {
 @Bean PasswordEncoder encoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService users(PasswordEncoder e){return new InMemoryUserDetailsManager(User.withUsername("user").password(e.encode("pwd")).roles("USER").build());}
 @Bean SecurityFilterChain chain(HttpSecurity h)throws Exception{return h.csrf(c->c.disable()).authorizeHttpRequests(a->a.anyRequest().authenticated()).httpBasic(org.springframework.security.config.Customizer.withDefaults()).build();}
 @Bean SecretKey jwtKey(){return Keys.hmacShaKeyFor("microservices-course-secret-key-32chars".getBytes(StandardCharsets.UTF_8));}
}
