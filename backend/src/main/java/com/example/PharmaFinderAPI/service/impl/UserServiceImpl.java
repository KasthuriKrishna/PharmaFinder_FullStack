package com.example.PharmaFinderAPI.service.impl;

import com.example.PharmaFinderAPI.dto.UserCreatedto;
import com.example.PharmaFinderAPI.dto.UserDTO;
import com.example.PharmaFinderAPI.entity.User;
import com.example.PharmaFinderAPI.repository.UserRepo;
import com.example.PharmaFinderAPI.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserCreatedto userCreateDTO) {
        User user = modelMapper.map(userCreateDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public List<UserCreatedto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserCreatedto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getUserDetails(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }
    @Override
    public UserDTO authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && password.equals(user.getPassword())) {
            return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getDob(), user.getMobileNumber(), user.getAddress(), user.getPincode());
        }
        return null;
    }
}