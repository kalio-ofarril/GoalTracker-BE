package com.goaltracker.GoalTracker.Services;

import com.goaltracker.GoalTracker.Data.DTOs.UserLoginDTO;
import com.goaltracker.GoalTracker.Data.Entities.UserLogin;
import com.goaltracker.GoalTracker.Utils.Exceptions.UserException;

public interface UserLoginService {

    UserLoginDTO login(UserLoginDTO userLoginDTO) throws UserException;

    UserLoginDTO loginGoogle(UserLoginDTO userLoginDTO) throws UserException;

    UserLogin signUp(UserLoginDTO userLoginDTO) throws UserException;

    UserLogin signUpGoogle(UserLoginDTO userLoginDTO) throws UserException;

}
