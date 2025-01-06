package com.goaltracker.GoalTracker.Data.Entities;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyActivitiesId implements Serializable {
    private int userId;
    private LocalDate activityDate;
}
