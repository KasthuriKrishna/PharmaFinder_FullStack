package com.example.PharmaFinderAPI.service;

import com.example.PharmaFinderAPI.dto.UserCreatedto;
import com.example.PharmaFinderAPI.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserCreatedto userCreateDTO);
    List<UserCreatedto> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO getUserDetails(Long id);
    UserDTO authenticateUser(String email, String password);
}
