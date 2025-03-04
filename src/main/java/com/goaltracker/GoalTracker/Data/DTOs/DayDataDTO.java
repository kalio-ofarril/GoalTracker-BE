package com.goaltracker.GoalTracker.Data.DTOs;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DayDataDTO {

    private int userId;
    private LocalDate activityDate;
    private String activities;
    private String goalActivities;
    private String comments;

}
