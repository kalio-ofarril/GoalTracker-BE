package com.goaltracker.GoalTracker.Data.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DayData")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DayData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long monthDataId;

    @Column(name = "UserId")
    private Long userId;

    @Column(name = "yearNumber")
    private int yearNumber;

    @Column(name = "MonthNumber")
    private int monthNumber;

    @Column(name = "DayNumber")
    private int dayNumber;

    @Column(name = "Activities")
    private String activities;

}
