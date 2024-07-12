package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.controller.ProfileController;
import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import com.example.elec5619fitnesswebapp.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProfileController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CurrentUserService currentUserService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;

    @MockBean
    private UserWorkoutService userWorkoutService;
    @MockBean
    private CalendarService calendarService;

    @MockBean
    private ModelAndViewService modelAndViewService;

    private User user;

    @BeforeEach
    public void setUp() {
        String email = "user@email.com";
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setEmail(email);
        this.user = user;
    }

    @Test
    public void profileTest() throws Exception {
        Map<String, String> modelExpected = new HashMap<>();
        modelExpected.put("firstName", user.getFirstName());
        modelExpected.put("lastName", user.getLastName());
        modelExpected.put("email", user.getEmail());

        given(currentUserService.getLoggedIn()).willReturn(true);
        given(currentUserService.getCurrentUser()).willReturn(user.getEmail());
        given(userRepository.findByEmail(user.getEmail())).willReturn(user);
        given(userService.getModelOfUser(user)).willReturn(modelExpected);

        mockMvc.perform(get("/profile"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("userProfile"))
                .andExpect(model().size(2))
                .andExpect(model().attribute("loggedIn", "true"))
                .andExpect(model().attribute("model", modelExpected));
    }

    @Test
    public void personalTrackerTest() throws Exception {
        given(userWorkoutService.getModelOfPersonalTracker(user, "")).willReturn(null);

        mockMvc.perform(get("/personalTracker"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("personalTracker"))
                .andExpect(model().size(2))
                .andExpect(model().attribute("loggedIn", "false"));
    }

    @Test
    public void workoutPlanTest() throws Exception {
        given(calendarService.getModelOfCalendar(user, "")).willReturn(null);

        mockMvc.perform(get("/workoutPlan"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("workoutPlanner"))
                .andExpect(model().size(2))
                .andExpect(model().attribute("loggedIn", "false"));
    }

    @Test
    public void changeStepDateTest() throws Exception {
        given(userWorkoutService.getModelOfPersonalTracker(user, "")).willReturn(null);

        mockMvc.perform(get("/changeStepDate").param("dateToView", "26-10-2023"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("personalTracker"))
                .andExpect(model().size(2));
    }

    @Test
    public void changePlanDateTest() throws Exception {
        given(calendarService.getModelOfCalendar(user, "")).willReturn(null);

        mockMvc.perform(get("/changePlanDate").param("dateToView", "26-10-2023"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("workoutPlanner"))
                .andExpect(model().size(2));
    }
}
