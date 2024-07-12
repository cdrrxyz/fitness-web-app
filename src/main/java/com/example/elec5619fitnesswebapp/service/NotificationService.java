package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.Groups;
import com.example.elec5619fitnesswebapp.model.Notification;
import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.GroupRepository;
import com.example.elec5619fitnesswebapp.repository.NotificationRepository;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private GroupMemberService groupMemberService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;

    public void addNotifications(Integer groupId, Integer createdBy, String message) {
        // Add notification for all users in group apart from createdBy
        // fetch all users in group
        List<Integer> groupMembers = groupMemberService.findAllUsersOfGroup(groupId);

        for (Integer userId : groupMembers) {
            System.out.println(userId);
            if (!userId.equals(createdBy)) {
                User user = userRepository.findUserById(userId);
                Groups group = groupRepository.findGroupsById(groupId);
                Notification notification = new Notification(user, group, createdBy, message);
                this.save(notification);
            }
        }
    }
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotifications(Integer groupId, Integer userId) {
        User user = userRepository.findUserById(userId);
        Groups group = groupRepository.findGroupsById(groupId);
        List<Notification> notifications = new ArrayList<>();
        notificationRepository.findByUserAndGroupAndIsRead(user, group, false)
                .forEach(notifications::add);
        for (Notification notification : notifications) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
    }

    public List<Notification> findAll(Integer userId) {
        User user = userRepository.findUserById(userId);
        List<Notification> notifications = new ArrayList<>();
        notificationRepository.findByUserAndIsReadOrderByGroup(user, false).forEach(notifications::add);
        notifications.forEach(notification -> notification.setGroupName(notification.getGroup().getName()));
        return notifications;
    }
}
