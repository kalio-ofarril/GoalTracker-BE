package com.goaltracker.GoalTracker.Data.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.goaltracker.GoalTracker.Data.Entities.DailyActivities;

public interface DailyActivitiesRepository extends JpaRepository<DailyActivities, Integer> {

    @Query(value = "SELECT * FROM daily_activities WHERE user_id = ?1 AND activity_date BETWEEN ?2 AND ?3", nativeQuery = true)
    List<DailyActivities> getMonthUserActivities(int userId, LocalDate starDate, LocalDate endDate);

    @Query(value = "SELECT * FROM daily_activities WHERE user_id = ?1 AND activity_date = ?2", nativeQuery = true)
    DailyActivities getDayUserActivities(int userId, LocalDate today);

}
