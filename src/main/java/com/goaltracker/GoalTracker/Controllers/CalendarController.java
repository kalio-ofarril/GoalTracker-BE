package com.goaltracker.GoalTracker.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goaltracker.GoalTracker.Data.DTOs.DayDataDTO;
import com.goaltracker.GoalTracker.Data.Entities.DayData;
import com.goaltracker.GoalTracker.Services.CalendarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/")
    public ResponseEntity<String> healthCheck() {

        return ResponseEntity.ok("Beating heart");
    }

    @GetMapping("/calendar/getMonthData")
    public ResponseEntity<List<DayData>> getMonthData() {

        return ResponseEntity.ok(calendarService.getMonthData());
    }

    @PostMapping("/calendar/createDailyEntry")
    public ResponseEntity<DayData> createDailyEntry(@RequestBody DayDataDTO dayDataDTO) {

        DayData dayData = calendarService.createDailyEntry(dayDataDTO);

        if (dayData != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(dayData);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}