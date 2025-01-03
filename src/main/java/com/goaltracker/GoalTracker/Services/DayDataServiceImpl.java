package com.goaltracker.GoalTracker.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goaltracker.GoalTracker.Data.DTOs.DayDataDTO;
import com.goaltracker.GoalTracker.Data.Entities.DailyActivities;
import com.goaltracker.GoalTracker.Data.Entities.DayData;
import com.goaltracker.GoalTracker.Data.Repositories.DailyActivitiesRepository;
import com.goaltracker.GoalTracker.Data.Repositories.DayDataRepository;

@Service
public class DayDataServiceImpl implements DayDataService {

    @Autowired
    private DayDataRepository dayDataRepository;

    @Autowired
    private DailyActivitiesRepository dailyActivitiesRepository;

    @Override
    public List<DayDataDTO> getMonthData(int userId, int monthNumber, int yearNumber) {

        List<DayDataDTO> monthData = new ArrayList<>();

        LocalDate thisMonth = LocalDate
                .parse(yearNumber + "-" + (monthNumber < 10 ? "0" + monthNumber : monthNumber) + "-01");

        Map<LocalDate, DailyActivities> dailyActivitiesMap = dailyActivitiesRepository
                .getMonthUserActivities(userId, thisMonth.minusMonths(1),
                        thisMonth.plusMonths(1).plusDays(thisMonth.plusMonths(1).lengthOfMonth() - 1))
                .stream().collect(Collectors.toMap(DailyActivities::getActivityDate, day -> day));

        for (DayData dayData : dayDataRepository.findMonthData(userId, thisMonth.minusMonths(1),
                thisMonth.plusMonths(1).plusDays(thisMonth.plusMonths(1).lengthOfMonth() - 1))) {

            DailyActivities dailyActivities = dailyActivitiesMap.get(dayData.getActivityDate());

            if (dailyActivities != null) {
                DayDataDTO dayDataDTO = DayDataDTO
                        .builder()
                        .userId(dayData.getUserId())
                        .activityDate(dayData.getActivityDate())
                        .activities(dayData.getActivities())
                        .goalActivities(dailyActivities.getActivities())
                        .comments(dayData.getComments())
                        .build();

                monthData.add(dayDataDTO);
            } else {
                System.out.println("-------------------------");
                System.out.println(dayData);
                System.out.println(dailyActivities);
            }

        }

        return monthData;
    }

    @Override
    public DayData getDayUserData(int userId, int day, int monthNumber, int yearNumber) {

        LocalDate today = LocalDate.parse(yearNumber + "-" + (monthNumber < 10 ? "0" + monthNumber : monthNumber) + "-"
                + (day < 10 ? "0" + day : day));

        return dayDataRepository.getDayUserActivities(userId, today);
    }

    @Override
    public DayData createDailyEntry(DayDataDTO dayDataDTO) {

        if (dayDataDTO != null) {
            DayData dayData = DayData.builder().activityDate(dayDataDTO.getActivityDate())
                    .activities(dayDataDTO.getActivities()).comments(dayDataDTO.getComments())
                    .userId(dayDataDTO.getUserId())
                    .build();

            return dayDataRepository.save(dayData);
        } else {
            return null;
        }
    }

}
