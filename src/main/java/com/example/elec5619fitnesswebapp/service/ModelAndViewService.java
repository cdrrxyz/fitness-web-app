package com.example.elec5619fitnesswebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ModelAndViewService {

    @Autowired
    private CurrentUserService currentUserService;
    public ModelAndView getModelAndView(String model) {
        ModelAndView modelAndView = new ModelAndView(model);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
        modelAndView.addObject("user_id", getUserId());
        return modelAndView;
    }

    public ModelAndView getModelAndView(String model, String object, String objectAttribute) {
        ModelAndView modelAndView = new ModelAndView(model, object, objectAttribute);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
        modelAndView.addObject("user_id", getUserId());
        return modelAndView;
    }

    public String getUserId() {
        if (currentUserService.getCurrentUser() != null) {
            return String.valueOf(currentUserService.getCurrentUid());
        }
        return "";
    }
}
