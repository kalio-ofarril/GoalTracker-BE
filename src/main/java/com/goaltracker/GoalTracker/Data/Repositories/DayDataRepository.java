package com.goaltracker.GoalTracker.Data.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.goaltracker.GoalTracker.Data.Entities.DayData;
import com.goaltracker.GoalTracker.Data.Entities.DayDataId;

public interface DayDataRepository extends JpaRepository<DayData, DayDataId> {

    List<DayData> findAll();

    @Query(value = "SELECT * FROM day_data WHERE user_id = ?1 AND activity_date BETWEEN ?2 AND ?3;", nativeQuery = true)
    List<DayData> findMonthData(int userId, LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT * FROM day_data WHERE user_id = ?1 AND activity_date = ?2;", nativeQuery = true)
    DayData getDayUserActivities(int userId, LocalDate date);

}
