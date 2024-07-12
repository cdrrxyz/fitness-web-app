package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.GroupMessages;
import com.example.elec5619fitnesswebapp.model.Groups;
import org.springframework.data.repository.CrudRepository;
public interface GroupMessagesRepository extends CrudRepository<GroupMessages, Integer> {
    Iterable<GroupMessages> findAllByGroup(Groups group);

    Iterable<GroupMessages> findAllByGroupOrderByCreatedDate(Groups group);
}
