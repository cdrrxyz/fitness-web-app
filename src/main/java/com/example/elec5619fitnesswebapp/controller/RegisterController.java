package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import com.example.elec5619fitnesswebapp.service.CurrentUserService;
import com.example.elec5619fitnesswebapp.service.EmailService;
import com.example.elec5619fitnesswebapp.pre.PreUser;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import com.example.elec5619fitnesswebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private ModelAndViewService modelAndViewService;

    @Autowired
    private UserRepository userRepository;
    private PreUser preUser;

    @RequestMapping(value = "/register", method= RequestMethod.GET)
    public ModelAndView register() {
        return modelAndViewService.getModelAndView("register");
    }

    @RequestMapping(value= "/register", method=RequestMethod.POST)
    public ModelAndView register(
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        if (userService.tryRegister(email)) {
            this.preUser = new PreUser();
            this.preUser.setEmail(email);
            this.preUser.setFirstName(firstName);
            this.preUser.setLastName(lastName);
            this.preUser.setPassword(password);

            // SEND Email Verification
            if (emailService.sendVerificationMail(this.preUser)) {
                return modelAndViewService.getModelAndView("emailValidation");
            }
        }
        return modelAndViewService.getModelAndView("register");
    }

    @RequestMapping(value = "/emailValidation", method = RequestMethod.POST)
    public ModelAndView emailVerification(
            @RequestParam("digit1") String digit1,
            @RequestParam("digit2") String digit2,
            @RequestParam("digit3") String digit3,
            @RequestParam("digit4") String digit4
    ) {

        String code = digit1 + digit2 + digit3 + digit4;
        if (emailService.registered(this.preUser, code)) {
            User user = userRepository.findByEmail(this.preUser.getEmail());

            currentUserService.setCurrentUser(user.getEmail());
            currentUserService.setLoggedIn(true);

            return modelAndViewService.getModelAndView("home");
        }
        return modelAndViewService.getModelAndView("emailValidation");
    }
}
