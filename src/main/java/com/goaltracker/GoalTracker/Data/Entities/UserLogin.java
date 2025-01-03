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
@Table(schema = "goal_tracker", name = "user_login")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "UserLoginId")
    private Long userLoginId;

    @Column(name = "UserId")
    private Long userId;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "IsGoogle")
    private Boolean isGoogle;

}
