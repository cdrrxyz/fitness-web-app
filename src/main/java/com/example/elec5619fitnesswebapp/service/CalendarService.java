package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.Calendar;
import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    public Map<String, String> getModelOfCalendar(User user, String s) {

        String now = String.valueOf(LocalDateTime.now());
        String nowEdit = now.substring(0, 10);
        List<Object[]> workoutPlans = calendarRepository.getWorkoutPlans(user.getId(), nowEdit);

        if (!s.equals("")) {
            workoutPlans = calendarRepository.getWorkoutPlans(user.getId(), s);
        }

        StringBuilder plans = new StringBuilder();
        for (Object[] result : workoutPlans) {
            String duration = String.valueOf(result[0]);
            String workoutType = (String) result[1];

            String temp = "<p>" + workoutType + ": " + duration + "mins" + "<p>";
            plans.append(temp);
        }
        String val = plans.toString();
        System.out.println(val);

        Map<String, String> model = new HashMap<>();
        model.put("plans", val);
        if (!s.equals("")) {
            model.put("date", s);
        }
        else {
            model.put("date", "Today");
        }

        return model;

    }

    @Transactional
    public int addWorkoutPlan(User user, String type, Integer duration, String date, Calendar cal) {

        cal.setUser(user);
        cal.setDuration(duration);
        cal.setWorkoutType(type);
        cal.setEntryTime(Timestamp.valueOf(date));

        return 0;
    }

    public LocalDateTime stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDate = LocalDateTime.parse(date, formatter);
        return localDate;
    }

}
