package com.cognizant.springlearn.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    @Bean UserDetailsService users(PasswordEncoder encoder) {
        UserDetails user=User.withUsername("user").password(encoder.encode("pwd")).roles("USER").build();
        UserDetails admin=User.withUsername("admin").password(encoder.encode("admin")).roles("USER","ADMIN").build();
        return new InMemoryUserDetailsManager(user,admin);
    }
    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwt) throws Exception {
        http.csrf(csrf -> csrf.disable()).sessionManagement(s -> s.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(a -> a.requestMatchers("/error").permitAll().anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults()).addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
