package com.goaltracker.GoalTracker.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goaltracker.GoalTracker.Data.DTOs.DailyActivitiesDTO;
import com.goaltracker.GoalTracker.Data.Entities.DailyActivities;
import com.goaltracker.GoalTracker.Data.Repositories.DailyActivitiesRepository;

@Service
public class DailyActivitiesServiceImpl implements DailyActivitiesService {

    @Autowired
    DailyActivitiesRepository dailyActivitiesRepository;

    @Override
    public List<DailyActivities> getMonthUserActivities(int userId, int monthNumber, int yearNumber) {

        LocalDate thisMonth = LocalDate.parse(yearNumber + "-" + monthNumber + "-01");

        return dailyActivitiesRepository.getMonthUserActivities(userId, thisMonth.minusMonths(1),
                thisMonth.plusMonths(1).plusDays(thisMonth.plusMonths(1).lengthOfMonth() - 1));
    }

    @Override
    public DailyActivities getDayUserActivities(int userId, int day, int monthNumber, int yearNumber) {

        LocalDate today = LocalDate.parse(yearNumber + "-" + (monthNumber < 10 ? "0" + monthNumber : monthNumber) + "-"
                + (day < 10 ? "0" + day : day));

        return dailyActivitiesRepository.getDayUserActivities(userId, today);
    }

    @Override
    public List<DailyActivities> saveAll(List<DailyActivitiesDTO> dailyActivitiesDTOList) {

        if (dailyActivitiesDTOList != null) {

            List<DailyActivities> activityList = new ArrayList<>();

            for (DailyActivitiesDTO dailyActivitiesDTO : dailyActivitiesDTOList) {
                DailyActivities dailyActivity = DailyActivities.builder().userId(dailyActivitiesDTO.getUserId())
                        .activityDate(dailyActivitiesDTO.getActivityDate())
                        .activities(dailyActivitiesDTO.getActivities()).build();

                activityList.add(dailyActivity);
            }

            return dailyActivitiesRepository.saveAll(activityList);
        } else {
            return null;
        }

    }

}
