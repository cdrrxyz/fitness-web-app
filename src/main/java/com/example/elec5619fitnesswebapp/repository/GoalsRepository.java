package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.Goals;
import com.example.elec5619fitnesswebapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface GoalsRepository extends CrudRepository<Goals, Integer> {
    Iterable<Goals> findAll();

    Iterable<Goals> findAllById(Integer id);
    Iterable<Goals> findAllByUser(User user);

    Goals findGoalsById(Integer id);
}
