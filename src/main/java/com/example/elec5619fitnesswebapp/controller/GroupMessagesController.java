package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.model.GroupMessages;
import com.example.elec5619fitnesswebapp.repository.GroupRepository;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import com.example.elec5619fitnesswebapp.service.GroupMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class GroupMessagesController {

    @Autowired
    private GroupMessagesService groupMessagesService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @CrossOrigin
    @PostMapping(value = "/send")
    public GroupMessages addGroupMessages(
            @RequestBody GroupMessages groupMessages
    ) {
        groupMessages.setCreatedDate(LocalDateTime.now());
        groupMessages.setUser(userRepository.findUserById(groupMessages.getCreatedBy()));
        groupMessages.setGroup(groupRepository.findGroupsById(groupMessages.getGroupId()));
        return groupMessagesService.save(groupMessages);
    }

    @CrossOrigin
    @GetMapping(value = "/find/{groupId}")
    public List<GroupMessages> findChatInGroup(
            @PathVariable int groupId
    ) {
        return groupMessagesService.findByGroupId(groupId);
    }
}
