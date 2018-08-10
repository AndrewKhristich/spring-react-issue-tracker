package com.example.demo.controllers;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // TODO change me
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    @PreAuthorize("isAnonymous()")
    public void authenticate(@RequestBody UserDTO userDTO) {
        userService.authenticate(userDTO.getUsername(), userDTO.getPassword());
    }

}
