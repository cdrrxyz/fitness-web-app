package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.Groups;
import com.example.elec5619fitnesswebapp.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public List<Groups> findall() {
        List<Groups> groups = new ArrayList<>();
        groupRepository.findAll().forEach(groups::add);
        return groups;
    }
    public Groups findById(Integer id) {
        Groups groups = null;
        Optional<Groups> result = groupRepository.findById(id);

        if (result.isPresent()) {
            groups = result.get();
        }
        else {
            throw new RuntimeException("Groups not found - Id: " + id);
        }

        return groups;
    }

    public Groups save(Groups groups) {
        Groups groupsSaved = groupRepository.save(groups);
        return groupsSaved;
    }

    public Groups edit(Groups groups) {
        return groupRepository.save(groups);
    }
}
