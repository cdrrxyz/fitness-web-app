package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.model.Calendar;
import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.model.UserWorkout;
import com.example.elec5619fitnesswebapp.repository.CalendarRepository;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import com.example.elec5619fitnesswebapp.repository.UserWorkoutRepository;
import com.example.elec5619fitnesswebapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class ProfileEditController {

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private UserWorkoutRepository userWorkoutRepository;

    @Autowired
    private UserWorkoutService userWorkoutService;

    @Autowired
    private GroupMessagesService groupMessagesService;

    @RequestMapping(value="/profileEdit", method = RequestMethod.GET)
    public ModelAndView profileEdit() {
        String email = currentUserService.getCurrentUser();
        User user = userRepository.findByEmail(email);
        Map<String, String> model = userService.getModelOfUser(user);

        ModelAndView modelAndView = new ModelAndView("userProfileEdit", "model", model);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
        return modelAndView;
    }

    @RequestMapping(value = "/profileEdit", method = RequestMethod.POST)
    public ModelAndView profileEdit(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("dateOfBirth") String dateOfBirth
            ) {

        String email = currentUserService.getCurrentUser();
        User user = userRepository.findByEmail(email);

        if (!dateOfBirth.isEmpty()) {
            LocalDate date = userService.stringToLocalDate(dateOfBirth);
            user.setDateOfBirth(date);
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userRepository.save(user);

        Map<String, String> model = userService.getModelOfUser(user);

        ModelAndView modelAndView = new ModelAndView("userProfile", "model", model);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
        return modelAndView;
    }

    @RequestMapping(value = "/stepsEdit", method = RequestMethod.POST)
    public ModelAndView stepsEdit(
            @RequestParam("stepsToday") String stepsToday,
            @RequestParam("dateOfExercise") String dateOfExercise,
            @RequestParam(value = "shareClicked", required = false) String shareClicked
    ) {

        String email = currentUserService.getCurrentUser();
        User user = userRepository.findByEmail(email);
        String now = String.valueOf(LocalDateTime.now());
        String nowEdit = now.substring(0, 10);

        UserWorkout uW = new UserWorkout();

        if (dateOfExercise.isEmpty()) {
            dateOfExercise = nowEdit + " 00:00:00";
        }
        else {
            dateOfExercise = dateOfExercise + " 00:00:00";
        }
        if (!stepsToday.equals("")) {
            Integer steps = Integer.parseInt(stepsToday);
            int success = userWorkoutService.setSteps(user, dateOfExercise, steps, uW);
        }

        userWorkoutRepository.save(uW);
        if (shareClicked != null) {
            groupMessagesService.sendPersonalMessageSteps(uW);
        }

        Map<String, String> model = userWorkoutService.getModelOfPersonalTracker(user, "");

        ModelAndView modelAndView = new ModelAndView("personalTracker", "model", model);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
        return modelAndView;
    }

    @RequestMapping(value = "/weightEdit", method = RequestMethod.POST)
    public ModelAndView weightEdit(
            @RequestParam("weight") String weight,
            @RequestParam("dateOfExercise") String dateOfExercise,
            @RequestParam(value = "shareClicked", required = false) String shareClicked
    ) {

        String email = currentUserService.getCurrentUser();
        User user = userRepository.findByEmail(email);
        String now = String.valueOf(LocalDateTime.now());
        String nowEdit = now.substring(0, 10);

        UserWorkout uW = new UserWorkout();

        if (dateOfExercise.isEmpty()) {
            dateOfExercise = nowEdit + " 00:00:00";
        }
        else {
            dateOfExercise = dateOfExercise + " 00:00:00";
        }
        if (!weight.equals("")) {
            Float weightInput = Float.parseFloat(weight);
            int success = userWorkoutService.setWeight(user, dateOfExercise, weightInput, uW);
        }

        userWorkoutRepository.save(uW);
        if (shareClicked != null) {
            groupMessagesService.sendPersonalMessageWeight(uW);
        }

        Map<String, String> model = userWorkoutService.getModelOfPersonalTracker(user, "");

        ModelAndView modelAndView = new ModelAndView("personalTracker", "model", model);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
        return modelAndView;
    }

    @RequestMapping(value = "/caloriesEdit", method = RequestMethod.POST)
    public ModelAndView caloriesEdit(
            @RequestParam("caloriesToday") String caloriesToday,
            @RequestParam("workouts") String workout,
            @RequestParam(value = "shareClicked", required = false) String shareClicked
    ) {

        String email = currentUserService.getCurrentUser();
        User user = userRepository.findByEmail(email);
        String now = String.valueOf(LocalDateTime.now());
        String nowEdit = now.substring(0, 10);
        String nowEdit2 = nowEdit + " 00:00:00";

        UserWorkout uW = new UserWorkout();

        if (!caloriesToday.equals("")) {
            Integer calories = Integer.parseInt(caloriesToday);
            int success = userWorkoutService.setCalories(user, nowEdit2, calories, workout, uW);
        }

        userWorkoutRepository.save(uW);
        if (shareClicked != null) {
            groupMessagesService.sendPersonalMessageCalories(uW);
        }

        Map<String, String> model = userWorkoutService.getModelOfPersonalTracker(user, "");

        ModelAndView modelAndView = new ModelAndView("personalTracker", "model", model);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
        return modelAndView;
    }

    @RequestMapping(value = "/workoutEdit", method = RequestMethod.POST)
    public ModelAndView workoutEdit(
            @RequestParam("duration") String duration,
            @RequestParam("workouts") String workout,
            @RequestParam("dateOfWorkout") String dateOfWorkout
    ) {

        String user1 = currentUserService.getCurrentUser();
        User user = userRepository.findByEmail(user1);

        Calendar calendar = new Calendar();

        dateOfWorkout = dateOfWorkout + " 00:00:00";

        if (!duration.equals("")) {
            Integer duration2 = Integer.parseInt(duration);
            int success = calendarService.addWorkoutPlan(user, workout, duration2, dateOfWorkout, calendar);
        }

        calendarRepository.save(calendar);

        Map<String, String> model = calendarService.getModelOfCalendar(user, "");
        ModelAndView modelAndView =  new ModelAndView("workoutPlanner", "model", model);
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));

        return modelAndView;

    }

}
