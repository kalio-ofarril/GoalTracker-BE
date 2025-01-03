package com.goaltracker.GoalTracker.Data.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserLoginDTO {

    private String email;
    private String password;
    private String userName;
    private Long userId;

}
