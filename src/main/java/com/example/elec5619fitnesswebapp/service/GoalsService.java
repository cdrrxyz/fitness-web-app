package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.Goals;
import com.example.elec5619fitnesswebapp.model.Groups;
import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.GoalsRepository;
import com.example.elec5619fitnesswebapp.repository.GroupRepository;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoalsService {
    @Autowired
    private GoalsRepository goalsRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;


    public List<Goals> findall() {
        List<Goals> goals = new ArrayList<>();
        goalsRepository.findAll().forEach(goals::add);
        return goals;
    }
    public Goals findById(Integer id) {
        Goals goals = null;
        Optional<Goals> result = goalsRepository.findById(id);

        if (result.isPresent()) {
            goals = result.get();
        }
        else {
            throw new RuntimeException("Goals not found - Id: " + id);
        }

        return goals;
    }
    public List<Goals> findAllByCreatedBy(Integer createdBy) {
        User user = userRepository.findUserById(createdBy);
        List<Goals> goals = new ArrayList<>();
        goalsRepository.findAllByUser(user).forEach(goals::add);
        return goals;
    }

    public Goals save(Goals goals) {
        Groups group = groupRepository.findGroupsById(goals.getGroupId());
        goals.setUser(userRepository.findUserById(goals.getCreatedBy()));
        goals.setGroup(group);
        goals.setGroupName(group.getName());
        Goals goalsSaved = goalsRepository.save(goals);
        System.out.println("Goals saved: " + goals);
        return goalsSaved;
    }

    public Goals edit(Goals goals) {
        Goals goal = goalsRepository.findGoalsById(goals.getId());
        goals.setUser(goal.getUser());
        goals.setGroup(goal.getGroup());
        goals.setGroupName(goal.getGroupName());
        return goalsRepository.save(goals);
    }
}
