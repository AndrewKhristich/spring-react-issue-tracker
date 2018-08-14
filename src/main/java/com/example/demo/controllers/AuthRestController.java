package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.utils.SecurityUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin(origins = "*") // TODO change me
public class AuthRestController {

    @GetMapping(value = "/check")
    public User checkAuth() {
        return SecurityUtil.getUserFromSession();
    }

}
