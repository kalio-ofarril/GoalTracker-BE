package com.goaltracker.GoalTracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.goaltracker.GoalTracker.Data.DTOs.UserLoginDTO;
import com.goaltracker.GoalTracker.Data.Entities.UserLogin;
import com.goaltracker.GoalTracker.Services.UserLoginService;
import com.goaltracker.GoalTracker.Utils.Exceptions.UserException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/users/login")
    private ResponseEntity login(@RequestBody UserLoginDTO userLoginDTO) throws UserException {
        log.info("In users login");
        log.info(userLoginDTO.toString());
        try {
            UserLoginDTO userLogin = userLoginService.login(userLoginDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userLogin);
        } catch (UserException e) {
            return ResponseEntity.status(501).body(e.getMessage());
        }
    }

    @PostMapping("/users/logingoogle")
    private ResponseEntity loginGoogle(@RequestBody UserLoginDTO userLoginDTO) throws UserException {
        try {
            UserLoginDTO userLogin = userLoginService.loginGoogle(userLoginDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userLogin);
        } catch (UserException e) {
            return ResponseEntity.status(501).body(e.getMessage());
        }
    }

    @PostMapping("users/signup")
    private ResponseEntity signUp(@RequestBody UserLoginDTO userLoginDTO) throws UserException {
        log.info("In users sign up");
        log.info(userLoginDTO.toString());
        try {
            UserLogin userLogin = userLoginService.signUp(userLoginDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(UserLoginDTO.builder()
                    .email(userLogin.getEmail())
                    .userId(userLogin.getUserId())
                    .userName(userLogin.getUserName())
                    .build());
        } catch (UserException e) {
            return ResponseEntity.status(501).body(e.getMessage());
        }
    }

    @PostMapping("users/signupgoogle")
    private ResponseEntity signUpGoogle(@RequestBody UserLoginDTO userLoginDTO) throws UserException {
        try {
            UserLogin userLogin = userLoginService.signUpGoogle(userLoginDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(UserLoginDTO.builder()
                    .email(userLogin.getEmail())
                    .userId(userLogin.getUserId())
                    .userName(userLogin.getUserName())
                    .build());
        } catch (UserException e) {
            return ResponseEntity.status(501).body(e.getMessage());
        }
    }

}
