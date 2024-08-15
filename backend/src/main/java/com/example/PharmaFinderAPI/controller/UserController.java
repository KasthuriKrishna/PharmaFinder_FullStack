package com.example.PharmaFinderAPI.controller;
import com.example.PharmaFinderAPI.dto.UserCreatedto;
import com.example.PharmaFinderAPI.dto.UserDTO;
import com.example.PharmaFinderAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
 
public class UserController {

    @Autowired
    private UserService userService;
    
    //create new user
    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserCreatedto userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }
    
    //fetch details of particular user
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //fetch details of particular user
    @GetMapping("/details/{id}")
    public UserDTO getUserDetails(@PathVariable Long id) {
        return userService.getUserDetails(id);
    }
    
    //Login validation
    @GetMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestParam String email, @RequestParam String password) {
        UserDTO userDTO = userService.authenticateUser(email, password);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO); // Success: returns UserDTO
        } else {
            return ResponseEntity.status(401).body("Invalid email or password"); // Failure: returns error message
        }
    }
}
