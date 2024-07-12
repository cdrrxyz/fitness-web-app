package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.GroupMember;
import com.example.elec5619fitnesswebapp.model.Groups;
import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.GroupMemberRepository;
import com.example.elec5619fitnesswebapp.repository.GroupRepository;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public void add(GroupMember groupMember) {
        groupMemberRepository.save(groupMember);
    }

    public List<Groups> findAllOfUser(Integer id) {
        Optional<User> result = userRepository.findById(id);
        User user = null;
        if (result.isPresent()) {
            user = result.get();
        }
        // find all groups for a user
        List<Integer> groupIds = new ArrayList<>();
        groupMemberRepository.findAllByUserId(user).forEach(groupMember -> groupIds.add(groupMember.getGroupId().getId()));
        System.out.println("groupIds " + groupIds);
        // get all groups with groupIds
        List<Groups> groups = new ArrayList<>();
        groupRepository.findAll().forEach(groups::add);
        System.out.println("groups " + groups);
        // filter to return only Groups that user is part of
        return groups.stream()
                .filter(g -> groupIds.contains(g.getId()))
                .collect(Collectors.toList());
    }

    public List<Integer> findAllUsersOfGroup(Integer groupId) {
        Optional<Groups> result = groupRepository.findById(groupId);
        Groups group = null;
        if (result.isPresent()) {
            group = result.get();
        }
        List<Integer> groupMembers = new ArrayList<>();
        groupMemberRepository.findAllByGroupId(group)
                .forEach(groupMember -> groupMembers.add(groupMember.getUserId().getId()));
        System.out.println(groupMembers);
        return groupMembers;
    }
}
