package com.goaltracker.GoalTracker.Services;

import java.util.List;

import com.goaltracker.GoalTracker.Data.DTOs.DailyActivitiesDTO;
import com.goaltracker.GoalTracker.Data.Entities.DailyActivities;

public interface DailyActivitiesService {

    List<DailyActivities> getMonthUserActivities(int userId, int month, int year);

    DailyActivities getDayUserActivities(int userId, int day, int month, int year);

    List<DailyActivities> saveAll(List<DailyActivitiesDTO> dailyActivitiesDTO);

}
