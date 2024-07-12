package com.example.elec5619fitnesswebapp.model;

import jakarta.persistence.*;

@Entity
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name="group_id", referencedColumnName = "id")
    private Groups groupId;

    public GroupMember() {
    }

    public GroupMember(User userId, Groups groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Groups getGroupId() {
        return groupId;
    }

    public void setGroupId(Groups groupId) {
        this.groupId = groupId;
    }
}
