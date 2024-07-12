package com.example.elec5619fitnesswebapp.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class GroupMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Groups group;
    @ManyToOne
    @JoinColumn(name="created_by_user_id", referencedColumnName = "id")
    private User user;
    private String message;
    private Timestamp createdDate;

    @Transient
    private Integer createdBy;

    @Transient
    private Integer groupId;

    public GroupMessages() {

    }

    public GroupMessages(Groups group, User user, String message, Timestamp createdDate, Integer createdBy, Integer groupId) {
        this.group = group;
        this.user = user;
        this.message = message;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = Timestamp.valueOf(createdDate);
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
