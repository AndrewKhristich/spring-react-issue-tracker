package com.example.demo.controllers.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "*") // TODO change me
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
