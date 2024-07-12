package com.example.elec5619fitnesswebapp.model;

import jakarta.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name="group_id", referencedColumnName = "id")
    private Groups group;

    private Integer messageAuthor;

    private String message;

    private boolean isRead;

    @Transient
    private String groupName;

    public Notification(User user, Groups group, Integer messageAuthor, String message) {
        this.user = user;
        this.group = group;
        this.messageAuthor = messageAuthor;
        this.message = message;
        this.isRead = false;
    }

    public Notification() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Integer getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(Integer messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
