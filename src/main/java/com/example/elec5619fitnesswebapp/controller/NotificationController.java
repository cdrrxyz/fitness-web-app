package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.model.GroupMessages;
import com.example.elec5619fitnesswebapp.model.Notification;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import com.example.elec5619fitnesswebapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ModelAndViewService modelAndViewService;
    @CrossOrigin
    @GetMapping()
    public ModelAndView notification() {

        return modelAndViewService.getModelAndView("notification");
    }
    @CrossOrigin
    @PostMapping(value = "/add")
    public void addNotification(
            @RequestBody GroupMessages groupMessages
    ) {

        System.out.println(groupMessages);
        System.out.println(groupMessages.getMessage());
        notificationService.addNotifications(groupMessages.getGroupId(),
                groupMessages.getCreatedBy(),
                groupMessages.getMessage());
}

    @CrossOrigin
    @GetMapping("/find/{userId}")
    public List<Notification> findNotification(
            @PathVariable int userId
    ) {
        return notificationService.findAll(userId);
    }

    @CrossOrigin
    @GetMapping("/delete/{groupId}/{userId}")
    public void deleteNotifications(
            @PathVariable int groupId,
            @PathVariable int userId
    ) {
        notificationService.deleteNotifications(groupId, userId);
    }
}
