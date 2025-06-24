package com.hahn.assignmenet.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hahn.assignmenet.Dtos.User.Request.LoginDTO;
import com.hahn.assignmenet.Dtos.User.Request.RegisterDTO;
import com.hahn.assignmenet.Dtos.User.Response.LoginResponseDTO;
import com.hahn.assignmenet.Dtos.User.Response.RegisterResponseDTO;
import com.hahn.assignmenet.Services.Interfaces.IUserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    private final IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return ResponseEntity.ok(userService.register(registerDTO));
    }
    
}