package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.controller.LogoutController;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = LogoutController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class LogoutControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModelAndViewService modelAndViewService;


    @Test
    public void logoutTest() throws Exception {
        ModelAndView expected = new ModelAndView("logout");

        given(modelAndViewService.getModelAndView("logout")).willReturn(expected);

        mockMvc.perform(get("/logout"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("logout"));
    }
}
