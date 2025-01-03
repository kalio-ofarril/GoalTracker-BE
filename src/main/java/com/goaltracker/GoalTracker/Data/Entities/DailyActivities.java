package com.goaltracker.GoalTracker.Data.Entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@IdClass(DailyActivitiesId.class)
@Table(schema = "goal_tracker", name = "daily_activities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DailyActivities {

    @Id
    @Column(name = "UserId")
    private int userId;

    @Id
    @Column(name = "ActivityDate")
    private LocalDate activityDate;

    @Column(name = "Activities")
    private String activities;

}

class DailyActivitiesId implements Serializable {

    private int userId;
    private LocalDate activityDate;
}