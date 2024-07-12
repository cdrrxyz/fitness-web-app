package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.GroupMessages;
import com.example.elec5619fitnesswebapp.model.Groups;
import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.model.UserWorkout;
import com.example.elec5619fitnesswebapp.repository.GroupMessagesRepository;
import com.example.elec5619fitnesswebapp.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupMessagesService {

    @Autowired
    private GroupMessagesRepository groupMessagesRepository;

    @Autowired
    private GroupMemberService groupMemberService;
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private NotificationService notificationService;
    public List<GroupMessages> findByGroupId(Integer groupId) {
        Optional<Groups> result = groupRepository.findById(groupId);
        Groups group = null;
        if (result.isPresent()) {
            group = result.get();
        }
        List<GroupMessages> groupMessages = new ArrayList<>();
        groupMessagesRepository.findAllByGroupOrderByCreatedDate(group)
                .forEach(groupMessages::add);
        groupMessages.forEach(groupMessage -> groupMessage.setCreatedBy(groupMessage.getUser().getId()));
        return groupMessages;
    }

    public void sendPersonalMessageWeight(UserWorkout userWorkout) {
        String date = String.valueOf(userWorkout.getEntryTime()).substring(0, 10);
        String msg = "Hey guys, as of " + date + " I weigh " + userWorkout.getWeight() + " kg!";
        this.sendMessageToGroups(userWorkout, msg);
    }

    public void sendPersonalMessageCalories(UserWorkout userWorkout) {
        String date = String.valueOf(userWorkout.getEntryTime()).substring(0, 10);
        String msg = "Hey guys, on " + date + " I burnt " + userWorkout.getCaloriesBurnt() + " calories while " +
                String.valueOf(userWorkout.getWorkoutType()).toLowerCase() + "!";
        this.sendMessageToGroups(userWorkout, msg);
    }

    public void sendPersonalMessageSteps(UserWorkout userWorkout) {
        String date = String.valueOf(userWorkout.getEntryTime()).substring(0, 10);
        String msg = "Hey guys, on " + date + " I did " + userWorkout.getSteps() + " steps!";
        this.sendMessageToGroups(userWorkout, msg);
    }

    public void sendMessageToGroups(UserWorkout userWorkout, String msg) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User user = userWorkout.getUser();

        // get all groups
        Iterable<Groups> groups = groupMemberService.findAllOfUser(userWorkout.getUser().getId());

        for (Groups group: groups) {
            GroupMessages groupMessage = new GroupMessages(group, user, msg, timestamp, user.getId(), group.getId());
            groupMessagesRepository.save(groupMessage);
            // Create Notification as well
            notificationService.addNotifications(groupMessage.getGroupId(),
                    groupMessage.getCreatedBy(),
                    groupMessage.getMessage());
        }
    }

    public GroupMessages save(GroupMessages groupMessages) {
        return groupMessagesRepository.save(groupMessages);
    }
}
