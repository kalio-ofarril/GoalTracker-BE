package com.goaltracker.GoalTracker.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goaltracker.GoalTracker.Data.DTOs.DailyActivitiesDTO;
import com.goaltracker.GoalTracker.Data.Entities.DailyActivities;
import com.goaltracker.GoalTracker.Data.Entities.DailyActivitiesId;
import com.goaltracker.GoalTracker.Data.Repositories.DailyActivitiesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DailyActivitiesServiceImpl implements DailyActivitiesService {

    @Autowired
    DailyActivitiesRepository dailyActivitiesRepository;

    @Override
    public List<DailyActivities> getMonthUserActivities(int userId, int monthNumber, int yearNumber) {

        LocalDate thisMonth = LocalDate.parse(yearNumber + "-" + monthNumber + "-01");

        return dailyActivitiesRepository.getMonthUserActivities(userId, thisMonth.minusMonths(1), thisMonth.plusMonths(1).plusDays(thisMonth.plusMonths(1).lengthOfMonth() - 1));
    }

    @Override
    public DailyActivities getDayUserActivities(int userId, int day, int monthNumber, int yearNumber) {

        LocalDate today = LocalDate.parse(yearNumber + "-" + (monthNumber < 10 ? "0" + monthNumber : monthNumber) + "-" + (day < 10 ? "0" + day : day));

        return dailyActivitiesRepository.getDayUserActivities(userId, today);
    }

    @Override
    public List<DailyActivities> saveAll(List<DailyActivitiesDTO> dailyActivitiesDTOList) {

        if (dailyActivitiesDTOList != null) {

            List<DailyActivities> activityList = new ArrayList<>();

            for (DailyActivitiesDTO dailyActivitiesDTO : dailyActivitiesDTOList) {

                if (dailyActivitiesDTO.getIsDelete() == null) {
                    Optional<DailyActivities> existingDailyActivities =
                            dailyActivitiesRepository
                                    .findById(
                                            DailyActivitiesId
                                                    .builder()
                                                    .activityDate(dailyActivitiesDTO.getActivityDate())
                                                    .userId(dailyActivitiesDTO.getUserId())
                                                    .build()
                                    );

                    List<String> newActivities = Stream.of(dailyActivitiesDTO.getActivities().split(","))
                            .map(act -> {
                                return (act.split("#")[0].trim());
                            })
                            .toList();

                    if (existingDailyActivities.isPresent()) {

                        List<String> existingActivities = Stream.of(existingDailyActivities.get().getActivities().split(","))
                                .map(act -> {
                                    return (act.split("#")[0].trim());
                                })
                                .toList();


                        List<String> activitiesToBeAdded = newActivities
                                .stream()
                                .filter(act -> {
                                    return (!existingActivities.contains(act));
                                })
                                .toList();

                        StringBuilder newActivityString = new StringBuilder(existingDailyActivities.get().getActivities());

                        List<String> activitiesToBeAddedFullString = activitiesToBeAdded
                                .stream()
                                .map(act -> {
                                    return (dailyActivitiesDTO.getActivities().split(",")[newActivities.indexOf(act)]);
                                })
                                .toList();

                        for (String act : activitiesToBeAddedFullString) {
                            newActivityString.append(",").append(act);
                        }
                        newActivityString = newActivityString.substring(0,1).equals(",")
                                ? new StringBuilder(newActivityString.substring(1, newActivityString.length()))
                                : newActivityString;
                        dailyActivitiesDTO.setActivities(String.valueOf(newActivityString));
                    }
                }

                DailyActivities dailyActivity = DailyActivities
                        .builder()
                        .userId(dailyActivitiesDTO.getUserId())
                        .activityDate(dailyActivitiesDTO.getActivityDate())
                        .activities(dailyActivitiesDTO.getActivities())
                        .build();

                activityList.add(dailyActivity);
            }

            return dailyActivitiesRepository.saveAll(activityList);
        } else {
            return null;
        }

    }

}
