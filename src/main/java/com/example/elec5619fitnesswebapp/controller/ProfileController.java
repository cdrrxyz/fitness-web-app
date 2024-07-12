package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import com.example.elec5619fitnesswebapp.service.CalendarService;
import com.example.elec5619fitnesswebapp.service.CurrentUserService;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import com.example.elec5619fitnesswebapp.service.UserService;
import com.example.elec5619fitnesswebapp.service.UserWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ProfileController {

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserWorkoutService userWorkoutService;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelAndViewService modelAndViewService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        if (currentUserService.getLoggedIn()) {

            String email = currentUserService.getCurrentUser();
            User user = userRepository.findByEmail(email);
            Map<String, String> model = userService.getModelOfUser(user);

            ModelAndView modelAndView = new ModelAndView("userProfile", "model", model);
            modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
            return modelAndView;
        }

        return modelAndViewService.getModelAndView("login");
    }

    @RequestMapping(value="/personalTracker", method = RequestMethod.GET)
    public ModelAndView personalTracker() {
        String email = currentUserService.getCurrentUser();
        User user = userRepository.findByEmail(email);

        String s = "";

        // Create Model for all personal tracker views
        Map<String, String> model = userWorkoutService.getModelOfPersonalTracker(user, s);
        ModelAndView modelAndView = new ModelAndView("personalTracker", "model", model);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));

        return modelAndView;
    }

    @RequestMapping(value="/workoutPlan", method = RequestMethod.GET)
    public ModelAndView workoutPlan() {
        String user1 = currentUserService.getCurrentUser();
        User user = userRepository.findByEmail(user1);

        String s = "";

        // Create Model for all personal tracker views
        Map<String, String> model = calendarService.getModelOfCalendar(user, s);
        ModelAndView modelAndView = new ModelAndView("workoutPlanner", "model", model);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));

        return modelAndView;
    }


    @RequestMapping(value="/changeStepDate", method = RequestMethod.GET)
    public ModelAndView changeStepDate(
            @RequestParam("dateToView") String dateToView
    ) {
        String user1 = currentUserService.getCurrentUser();
        User user = userRepository.findByEmail(user1);

        // Create Model for all personal tracker views
        Map<String, String> model = userWorkoutService.getModelOfPersonalTracker(user, dateToView);

        ModelAndView modelAndView = new ModelAndView("personalTracker", "model", model);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
        return modelAndView;
    }

    @RequestMapping(value="/changePlanDate", method = RequestMethod.GET)
    public ModelAndView changePlanDate(
            @RequestParam("dateToView") String dateToView
    ) {
        String user1 = currentUserService.getCurrentUser();
        User user = userRepository.findByEmail(user1);

        // Create Model for all personal tracker views
        Map<String, String> model = calendarService.getModelOfCalendar(user, dateToView);
        ModelAndView modelAndView = new ModelAndView("workoutPlanner", "model", model);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));

        return modelAndView;
    }

}
