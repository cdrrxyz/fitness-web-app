package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    @Autowired
    private UserRepository userRepository;

    private String currentUser;
    private boolean loggedIn;

    public String getCurrentUser() {
        return currentUser;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Integer getCurrentUid() {
        User u = userRepository.findByEmail(currentUser);
        return u.getId();
    }
}
