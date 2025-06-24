package com.hahn.assignmenet.Services.Implementations;

import com.hahn.assignmenet.Dtos.User.Request.LoginDTO;
import com.hahn.assignmenet.Dtos.User.Request.RegisterDTO;
import com.hahn.assignmenet.Dtos.User.Response.LoginResponseDTO;
import com.hahn.assignmenet.Dtos.User.Response.RegisterResponseDTO;
import com.hahn.assignmenet.Entities.User;
import com.hahn.assignmenet.Repositories.UserRepository;
import com.hahn.assignmenet.Services.Utils.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, modelMapper, jwtService, passwordEncoder);
    }

    @Test
    void testRegister_Success() {
        RegisterDTO registerDTO = new RegisterDTO();
        User user = new User();
        when(modelMapper.map(registerDTO, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        RegisterResponseDTO response = userService.register(registerDTO);
        assertEquals("user created successfully", response.getMessage());
    }

    @Test
    void testLogin_Success() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("password");
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn("token");
        LoginResponseDTO response = userService.login(loginDTO);
        assertEquals("test@example.com", response.getEmail());
        assertEquals("token", response.getToken());
    }

    @Test
    void testLogin_InvalidPassword() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("wrong");
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "encodedPassword")).thenReturn(false);
        assertThrows(RuntimeException.class, () -> userService.login(loginDTO));
    }
}
