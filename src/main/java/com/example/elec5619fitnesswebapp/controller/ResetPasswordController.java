package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResetPasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelAndViewService modelAndViewService;
    @RequestMapping(value = "/resetPassword", method= RequestMethod.GET)
    public ModelAndView resetPassword() {
        return modelAndViewService.getModelAndView("resetPassword", "errorMsg", "");
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ModelAndView resetPassword(
            @RequestParam("email") String email,
            @RequestParam("password1") String password1,
            @RequestParam("password2") String password2
    ) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            return modelAndViewService.getModelAndView("resetPassword", "errorMsg", "Email does not exist");

        } else if (password1.equals(password2)) {
            user.resetPassword(password2);
            userRepository.save(user);
            return modelAndViewService.getModelAndView("login");
        }
        return modelAndViewService.getModelAndView("resetPassword", "errorMsg", "Passwords do not match");
    }
}
