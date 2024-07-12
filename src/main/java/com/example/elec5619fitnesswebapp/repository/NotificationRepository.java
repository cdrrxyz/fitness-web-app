package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.Groups;
import com.example.elec5619fitnesswebapp.model.Notification;
import com.example.elec5619fitnesswebapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {

    Iterable<Notification> findByUserAndGroupAndIsRead(User user, Groups group, boolean isRead);

    Iterable<Notification> findByUserAndIsReadOrderByGroup(User user, boolean isRead);

}
