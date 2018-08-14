package com.example.demo.controllers;

import com.example.demo.dto.SignInRequest;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // TODO change me
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public void saveUser(@RequestBody SignInRequest dto) {
        userService.saveUser(dto.getUsername(), dto.getPassword());
        userService.authenticate(dto.getUsername(), dto.getPassword());
    }

}
