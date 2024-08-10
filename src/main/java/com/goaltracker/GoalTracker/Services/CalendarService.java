package com.goaltracker.GoalTracker.Services;

import java.util.List;

import com.goaltracker.GoalTracker.Data.DTOs.DayDataDTO;
import com.goaltracker.GoalTracker.Data.Entities.DayData;

public interface CalendarService {

    List<DayData> getMonthData();

    DayData createDailyEntry(DayDataDTO dayData);

}
