package com.goaltracker.GoalTracker.Services;

import java.util.List;

import com.goaltracker.GoalTracker.Data.DTOs.DayDataDTO;
import com.goaltracker.GoalTracker.Data.Entities.DayData;

public interface DayDataService {

    List<DayDataDTO> getMonthData(int userId, int monthNumber, int yearNumber);

    DayData getDayUserData(int userId, int day, int monthNumber, int yearNumber);

    DayData createDailyEntry(DayDataDTO dayData);

}
