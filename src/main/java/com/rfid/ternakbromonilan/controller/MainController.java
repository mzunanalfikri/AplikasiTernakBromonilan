package com.rfid.ternakbromonilan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Principal principal){
        if (principal == null){
            return "root";
        }
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
