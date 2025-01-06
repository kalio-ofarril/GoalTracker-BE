package com.goaltracker.GoalTracker.Data.Entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@IdClass(DayDataId.class)
@Table(schema = "goal_tracker", name = "day_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DayData {

    @Id
    @Column(name = "UserId")
    private int userId;

    @Id
    @Column(name = "ActivityDate")
    private LocalDate activityDate;

    @Column(name = "Activities")
    private String activities;

    @Column(name = "Comments")
    private String comments;

}
