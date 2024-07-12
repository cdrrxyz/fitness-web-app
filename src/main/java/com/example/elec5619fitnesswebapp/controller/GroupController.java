package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.model.GroupMember;
import com.example.elec5619fitnesswebapp.model.Groups;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import com.example.elec5619fitnesswebapp.service.GroupMemberService;
import com.example.elec5619fitnesswebapp.service.GroupService;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private ModelAndViewService modelAndViewService;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping
    public ModelAndView groups() {
        return modelAndViewService.getModelAndView("groups");
    }

    @CrossOrigin
    @GetMapping(value = "/all")
    public List<Groups> findAll() {
        return groupService.findall();
    }

    @CrossOrigin
    @GetMapping(value = "/all/{userId}")
    public List<Groups> findAllOfUser(
            @PathVariable int userId
    ) {
        List<Groups> result = groupMemberService.findAllOfUser(userId);
        return result;
    }

    @CrossOrigin
    @PostMapping(value = "/add")
    public Groups addGroup(
            @RequestBody Groups groups
    ) {
        groups.setCreateDate(LocalDate.now());
        groups.setUser(userRepository.findUserById(groups.getCreatedBy()));
        Groups newGroup = groupService.save(groups);

        if (newGroup != null) {
            // Add Group Members
            for (Integer userId : groups.getUserIds()) {
                System.out.println("ADDING USER");
                GroupMember groupMember = new GroupMember(newGroup.getUser(), newGroup);
                groupMemberService.add(groupMember);
            }
        }
        return newGroup;
    }

    @CrossOrigin
    @PostMapping(value = "/edit")
    public Groups editGroup(
            @RequestBody Groups groups
    ) {
        return groupService.edit(groups);
    }
}
