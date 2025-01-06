package com.goaltracker.GoalTracker.Data.DTOs;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DailyActivitiesDTO {

    private int userId;
    private LocalDate activityDate;
    private String activities;
    private Boolean isDelete;

}
