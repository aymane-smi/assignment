package com.hahn.assignmenet.Services.Interfaces;

import com.hahn.assignmenet.Dtos.User.Request.LoginDTO;
import com.hahn.assignmenet.Dtos.User.Request.RegisterDTO;
import com.hahn.assignmenet.Dtos.User.Response.LoginResponseDTO;
import com.hahn.assignmenet.Dtos.User.Response.RegisterResponseDTO;

public interface IUserService {
    public LoginResponseDTO login(LoginDTO loginDTO);
    public RegisterResponseDTO register(RegisterDTO registerDTO);
}