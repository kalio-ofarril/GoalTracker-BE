package com.goaltracker.GoalTracker.Data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goaltracker.GoalTracker.Data.Entities.MonthData;

public interface CalendarRepository extends JpaRepository<MonthData, Long> {

}
