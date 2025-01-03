package com.goaltracker.GoalTracker.Controllers;

import java.util.List;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goaltracker.GoalTracker.Data.DTOs.DayDataDTO;
import com.goaltracker.GoalTracker.Data.Entities.DailyActivities;
import com.goaltracker.GoalTracker.Data.Entities.DayData;
import com.goaltracker.GoalTracker.Services.DailyActivitiesService;
import com.goaltracker.GoalTracker.Services.DayDataService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DayController {

    private final DayDataService dayDataService;

    @GetMapping("/")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Beating heart");
    }

    @GetMapping("/calendar/getMonthData")
    public ResponseEntity<List<DayDataDTO>> getMonthData(@RequestParam int userId, @RequestParam int monthNumber,
            @RequestParam int yearNumber) {

        log.atInfo().setMessage("Searching data for user {}: Month {}, Year {}.").addArgument(userId)
                .addArgument(monthNumber).addArgument(yearNumber).log();
        return ResponseEntity.ok(dayDataService.getMonthData(userId, monthNumber, yearNumber));
    }

    @GetMapping("/calendar/getDayData")
    public ResponseEntity<DayData> getDayUserActivities(@RequestParam int userId,
            @RequestParam int month,
            @RequestParam int year,
            @RequestParam int day) {

        DayData dayData = dayDataService.getDayUserData(userId, day, month, year);

        if (dayData != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(dayData);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/calendar/createDailyEntry")
    public ResponseEntity<DayData> createDailyEntry(@RequestBody DayDataDTO dayDataDTO) {

        log.atInfo().setMessage("Saving entry for DayDataDTO: {}").addArgument(dayDataDTO.toString()).log();
        DayData dayData = dayDataService.createDailyEntry(dayDataDTO);

        if (dayData != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(dayData);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}