package com.goaltracker.GoalTracker.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goaltracker.GoalTracker.Data.DTOs.DailyActivitiesDTO;
import com.goaltracker.GoalTracker.Data.Entities.DailyActivities;
import com.goaltracker.GoalTracker.Services.DailyActivitiesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DailyActivitiesController {

    @Autowired
    DailyActivitiesService DailyActivitiesService;

    @GetMapping("/calendar/getActivities")
    public ResponseEntity<List<DailyActivities>> getMonthUserActivities(@RequestParam int userId,
            @RequestParam int month,
            @RequestParam int year) {

        List<DailyActivities> monthActivities = DailyActivitiesService.getMonthUserActivities(userId, month, year);

        if (monthActivities != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(monthActivities);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/calendar/getDayActivities")
    public ResponseEntity<DailyActivities> getDayUserActivities(@RequestParam int userId,
            @RequestParam int month,
            @RequestParam int year,
            @RequestParam int day) {

        DailyActivities monthActivities = DailyActivitiesService.getDayUserActivities(userId, day, month, year);

        if (monthActivities != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(monthActivities);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/calendar/setActivities", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DailyActivities>> setMonthActivities(
            @RequestBody List<DailyActivitiesDTO> monthActivitiesDTO) {

        List<DailyActivities> monthActivitiesResp = DailyActivitiesService.saveAll(monthActivitiesDTO);

        if (monthActivitiesResp != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(monthActivitiesResp);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
