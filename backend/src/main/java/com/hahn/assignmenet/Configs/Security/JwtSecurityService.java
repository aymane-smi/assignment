package com.hahn.assignmenet.Configs.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class JwtSecurityService {
    @Autowired
    private final JwtVerification filterSecurity;
    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(crsf -> crsf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(filterSecurity, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
