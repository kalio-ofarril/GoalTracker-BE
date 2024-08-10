package com.goaltracker.GoalTracker.Data.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayDataDTO {

    private Long monthDataId;
    private Long userId;
    private int yearNumber;
    private int monthNumber;
    private int dayNumber;
    private String activities;

}
