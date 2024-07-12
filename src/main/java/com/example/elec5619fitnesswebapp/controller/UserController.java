package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }
}
