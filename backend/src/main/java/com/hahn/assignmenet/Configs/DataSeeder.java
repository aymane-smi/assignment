package com.hahn.assignmenet.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hahn.assignmenet.Entities.User;
import com.hahn.assignmenet.Repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception{
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword(passwordEncoder.encode("password123"));
        userRepository.save(user);
    }
    
}
