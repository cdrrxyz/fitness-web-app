package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.model.UserWorkout;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import com.example.elec5619fitnesswebapp.repository.UserWorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserWorkoutService {

    @Autowired
    private UserWorkoutRepository userWorkoutRepository;

    @Autowired
    UserRepository userRepository;

    public Map<String, String> getModelOfPersonalTracker(User user, String s) {

        Float currentWeight = userWorkoutRepository.getCurrentWeight(user.getId());

        String now = String.valueOf(LocalDateTime.now());
        String nowEdit = now.substring(0, 10);
        Integer steps = userWorkoutRepository.getStepsOnDate(user.getId(), nowEdit);

        if (!s.equals("")) {
            steps = userWorkoutRepository.getStepsOnDate(user.getId(), s);
        }

        Integer caloriesBurnt = userWorkoutRepository.getCaloriesBurntOnDate(user.getId(), nowEdit);
        List<Object[]> caloriesAndExercises = userWorkoutRepository.getCaloriesAndExercises(user.getId(), nowEdit);
        List<Object[]> weightOverTime = userWorkoutRepository.getWeightOverTime(user.getId());

        StringBuilder calAndEx = new StringBuilder();
        for (Object[] result : caloriesAndExercises) {
            String cals = String.valueOf(result[0]);
            String workoutType = (String) result[1];

            String temp = "<p>" + workoutType + ": " + cals + "<p>";
            calAndEx.append(temp);
        }

        StringBuilder weightEntryTimes = new StringBuilder();
        StringBuilder weightData = new StringBuilder();

        for (Object[] result : weightOverTime) {
            String entry = String.valueOf(result[0]);
            String data = String.valueOf(result[1]);

            String temp = entry + "*";
            String temp2 = data + "*";

            weightEntryTimes.append(temp);
            weightData.append(temp2);
        }

        Map<String, String> model = new HashMap<>();
        model.put("currentWeight", String.valueOf(currentWeight));
        model.put("caloriesBurnt", String.valueOf(caloriesBurnt));
        model.put("caloriesAndExercises", calAndEx.toString());
        model.put("steps", String.valueOf(steps));
        model.put("weightEntryTimes", weightEntryTimes.toString());
        model.put("weightEntryData", weightData.toString());
        if (!s.equals("")) {
            model.put("date", s);
        }
        else {
            model.put("date", "Today");
        }


        return model;
    }
    @Transactional
    public int setSteps(User user, String nowEdit, Integer steps, UserWorkout uW) {
        uW.setSteps(steps);
        uW.setUser(user);
        LocalDateTime date = stringToLocalDate(nowEdit);
        uW.setEntryTime(date);

        return 0;
    }

    @Transactional
    public int setCalories(User user, String nowEdit, Integer calories, String type, UserWorkout uW) {
        uW.setUser(user);
        LocalDateTime date = stringToLocalDate(nowEdit);
        uW.setEntryTime(date);
        uW.setCaloriesBurnt(calories);
        uW.setWorkoutType(type);

        return 0;
    }

    @Transactional
    public int setWeight(User user, String nowEdit, Float weight, UserWorkout uW) {
        uW.setWeight(weight);
        LocalDateTime date = stringToLocalDate(nowEdit);
        uW.setEntryTime(date);
        uW.setUser(user);

        return 0;
    }

    public LocalDateTime stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDate = LocalDateTime.parse(date, formatter);
        return localDate;
    }

    @Transactional
    public Map<String, String> getTopThreeCalories() {
        List<Object[]> topThreeCalories = userWorkoutRepository.getTopThreeCalories();
        Map<String, String> model = new HashMap<>();
        int count = 1;
        for (Object[] result : topThreeCalories) {
            User user = userRepository.findUserById((Integer) result[0]);
            String fullName = count + user.getFirstName() + " " + user.getLastName();
            model.put(fullName, String.valueOf(result[1]));
            count++;
        }

        return model;
    }


}

