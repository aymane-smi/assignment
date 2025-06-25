package com.hahn.assignmenet.Services.Implementations;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hahn.assignmenet.Dtos.User.Request.LoginDTO;
import com.hahn.assignmenet.Dtos.User.Request.RegisterDTO;
import com.hahn.assignmenet.Dtos.User.Response.LoginResponseDTO;
import com.hahn.assignmenet.Dtos.User.Response.RegisterResponseDTO;
import com.hahn.assignmenet.Entities.User;
import com.hahn.assignmenet.Repositories.UserRepository;
import com.hahn.assignmenet.Services.Interfaces.IUserService;
import com.hahn.assignmenet.Services.Utils.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) {
        UserDetails user = loadUserByUsername(loginDTO.getEmail());
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return LoginResponseDTO.builder()
                               .email(user.getUsername())
                               .token(jwtService.generateToken(user))
                               .build();
    }

    @Override
    public RegisterResponseDTO register(RegisterDTO registerDTO) {
        User user = modelMapper.map(registerDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return RegisterResponseDTO.builder().message("user created successfully")
                                  .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> OptionalUser = userRepository.findByEmail(username);
        if(OptionalUser.isEmpty())
            throw new RuntimeException("email not found");
        User user = OptionalUser.get();
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), 
            user.getPassword(), 
            java.util.Collections.emptyList()
        );
    }


}
