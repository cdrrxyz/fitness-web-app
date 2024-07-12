package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.GroupMember;
import com.example.elec5619fitnesswebapp.model.Groups;
import com.example.elec5619fitnesswebapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface GroupMemberRepository extends CrudRepository<GroupMember, Integer> {
    Iterable<GroupMember> findAllByUserId(User userId);

    Iterable<GroupMember> findAllByGroupId(Groups groupId);
}
