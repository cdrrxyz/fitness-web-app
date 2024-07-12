package com.example.elec5619fitnesswebapp.pre;

import com.example.elec5619fitnesswebapp.model.User;
public class PreUserToUser {

    public static User toUser(PreUser preUser) {
        User user = new User();

        user.setEmail(preUser.getEmail());
        user.setPassword(preUser.getPassword());
        user.setFirstName(preUser.getFirstName());
        user.setLastName(preUser.getLastName());
        user.setDateOfBirth(preUser.getDateOfBirth());

        return user;
    }
}
