package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import com.example.elec5619fitnesswebapp.service.UserWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    @Autowired
    private UserWorkoutService userWorkoutService;

    @Autowired
    private ModelAndViewService modelAndViewService;

    @CrossOrigin
    @GetMapping
    public ModelAndView goals() {
        return modelAndViewService.getModelAndView("leaderboard");
    }

    @CrossOrigin
    @GetMapping("/topThree")
    public Map<String, String> findTopThree() {
        return userWorkoutService.getTopThreeCalories();
    }


}
