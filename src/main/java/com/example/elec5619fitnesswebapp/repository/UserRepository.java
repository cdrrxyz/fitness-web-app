package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    User findUserById(int id);
}
