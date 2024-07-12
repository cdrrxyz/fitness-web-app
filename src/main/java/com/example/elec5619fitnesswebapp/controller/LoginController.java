package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import com.example.elec5619fitnesswebapp.service.CurrentUserService;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import com.example.elec5619fitnesswebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private ModelAndViewService modelAndViewService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value="/login", method= RequestMethod.GET)
    public ModelAndView login() {
        return modelAndViewService.getModelAndView("login");
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public ModelAndView login(
            @RequestParam("email") String email,
            @RequestParam("password") String password
            ) {
        if (userService.tryLogin(email, password)) {

            User user = userRepository.findByEmail(email);

            currentUserService.setCurrentUser(email);
            currentUserService.setLoggedIn(true);
            return modelAndViewService.getModelAndView("home", "email", email);

        } else {
            return modelAndViewService.getModelAndView("login", "message", "Incorrect email or password");
        }
    }
}
