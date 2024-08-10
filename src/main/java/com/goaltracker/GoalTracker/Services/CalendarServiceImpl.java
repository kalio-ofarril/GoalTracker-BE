package com.goaltracker.GoalTracker.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goaltracker.GoalTracker.Data.Repositories.CalendarRepository;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    @Override
    public List<String> getMonthData() {

        List<String> data = new ArrayList<>();

        data.add("Hello!");

        return data;
    }

}
