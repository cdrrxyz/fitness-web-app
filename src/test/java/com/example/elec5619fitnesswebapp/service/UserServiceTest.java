package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import com.example.elec5619fitnesswebapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        String email = "user@email.com";
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setEmail(email);
        user.setPassword("$2a$10$42LyzVhgZO3ao./fnPux.eV5tTnKS62cwOiThWPH9Dz9SJhokl.Dm");
        this.user = user;
    }

    @Test
    public void tryLoginTrueTest() {

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        assertTrue(userService.tryLogin(user.getEmail(), "password"));
    }

    @Test
    public void tryLoginFalseTest() {

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        assertFalse(userService.tryLogin(user.getEmail(), "123"));
    }

    @Test
    public void getModelOfUserTest() {
        Map<String, String> expected = new HashMap<>();
        expected.put("firstName", user.getFirstName());
        expected.put("lastName", user.getLastName());
        expected.put("email", user.getEmail());

        Map<String, String> result = userService.getModelOfUser(user);
        assertEquals(expected, result);
    }

    @Test
    public void stringToLocalDateTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expected = LocalDate.parse("2023-10-26", formatter);

        LocalDate result = userService.stringToLocalDate("2023-10-26");
        assertEquals(expected, result);
    }

    @Test
    public void findAllTest() {
        List<User> expected = new ArrayList<>();
        expected.add(user);

        when(userRepository.findAll()).thenReturn(expected);

        List<User> result = userService.findAll();
        assertEquals(expected, result);
    }
}
