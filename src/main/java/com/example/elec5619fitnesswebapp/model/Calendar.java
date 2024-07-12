package com.example.elec5619fitnesswebapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    private Integer duration;

    private Timestamp entryTime;

    private String workoutType;

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Integer getDuration() {
        return duration;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }
}
