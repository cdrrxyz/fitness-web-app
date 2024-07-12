package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.service.CurrentUserService;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private ModelAndViewService modelAndViewService;

    @RequestMapping(value = {"/", "home"}, method= RequestMethod.GET)
    public ModelAndView home() {
        return modelAndViewService.getModelAndView("home");
    }

    @RequestMapping(value="/home", method = RequestMethod.POST)
    public ModelAndView logout(
            @RequestParam("logout") boolean result
    ) {
        if (result) {
            if (currentUserService.getLoggedIn()) {
                currentUserService.setCurrentUser(null);
                currentUserService.setLoggedIn(false);
            }
        }
        return modelAndViewService.getModelAndView("home");
    }
}
