package com.goaltracker.GoalTracker.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goaltracker.GoalTracker.Data.DTOs.DayDataDTO;
import com.goaltracker.GoalTracker.Data.Entities.DayData;
import com.goaltracker.GoalTracker.Data.Repositories.CalendarRepository;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    @Override
    public List<DayData> getMonthData() {
        return calendarRepository.findAll();
    }

    @Override
    public DayData createDailyEntry(DayDataDTO dayDataDTO) {

        if (dayDataDTO != null) {
            DayData dayData = DayData.builder()
                    .yearNumber(dayDataDTO.getYearNumber())
                    .monthNumber(dayDataDTO.getMonthNumber())
                    .dayNumber(dayDataDTO.getDayNumber())
                    .activities(dayDataDTO.getActivities())
                    .userId(dayDataDTO.getUserId())
                    .build();

            return calendarRepository.save(dayData);
        }else{
            return null;
        }
    }

}
