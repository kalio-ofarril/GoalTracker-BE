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
@Table(name = "monthData")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MonthData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long monthDataId;

    @Column(name = "UserId")
    private Long userId;

    @Column(name = "MonthNumber")
    private int monthNumber;

    @Column(name = "MonthDay")
    private int monthDay;

    @Column(name = "Activities")
    private String activities;

}
