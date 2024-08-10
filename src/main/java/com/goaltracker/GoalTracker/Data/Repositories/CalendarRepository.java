package com.goaltracker.GoalTracker.Data.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goaltracker.GoalTracker.Data.Entities.DayData;

public interface CalendarRepository extends JpaRepository<DayData, Long> {

    List<DayData> findAll();

}
