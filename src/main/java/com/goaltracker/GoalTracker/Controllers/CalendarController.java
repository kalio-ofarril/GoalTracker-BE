package com.goaltracker.GoalTracker.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<String>> getMonthData() {

        return ResponseEntity.ok(calendarService.getMonthData());
    }
}