package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.controller.LoginController;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import com.example.elec5619fitnesswebapp.service.CurrentUserService;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import com.example.elec5619fitnesswebapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = LoginController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private CurrentUserService currentUserService;
    @MockBean
    private ModelAndViewService modelAndViewService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void loginGetTest() throws Exception {
        ModelAndView expected = new ModelAndView("login");

        given(modelAndViewService.getModelAndView("login")).willReturn(expected);
        given(currentUserService.getLoggedIn()).willReturn(true);

        mockMvc.perform(get("/login"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("login"));
    }

    @Test
    public void loginPostValidTest() throws Exception {
        ModelAndView expected = new ModelAndView("home");

        given(userService.tryLogin("user", "123")).willReturn(true);
        given(userRepository.findByEmail("user")).willReturn(null);
        given(modelAndViewService.getModelAndView("home", "email", "user")).willReturn(expected);

        mockMvc.perform(post("/login")
                        .param("email", "user")
                        .param("password", "123"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("home"));
    }
}
