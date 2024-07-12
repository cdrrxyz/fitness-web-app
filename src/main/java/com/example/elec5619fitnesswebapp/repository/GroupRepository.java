package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.Groups;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Groups, Integer> {
    Iterable<Groups> findAll();

    Iterable<Groups> findAllById(Integer id);

    Groups findGroupsById(int id);
}
