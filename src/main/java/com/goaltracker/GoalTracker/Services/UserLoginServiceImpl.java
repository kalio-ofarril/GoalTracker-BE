package com.goaltracker.GoalTracker.Services;

import java.util.List;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goaltracker.GoalTracker.Data.DTOs.UserLoginDTO;
import com.goaltracker.GoalTracker.Data.Entities.UserLogin;
import com.goaltracker.GoalTracker.Data.Repositories.UserLoginRepository;
import com.goaltracker.GoalTracker.Utils.Exceptions.UserException;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    private UserLoginRepository userLoginRepository;
    private StandardPBEStringEncryptor encryptor;

    @Autowired
    UserLoginServiceImpl(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword("pwdGoalTracker1");
        this.encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
    }

    @Override
    public UserLoginDTO login(UserLoginDTO userLoginDTO) throws UserException {
        if (userLoginDTO != null) {
            UserLogin userLogin = userLoginRepository.getNonGoogleUserLogin(userLoginDTO.getEmail());
            if (userLogin != null) {
                if (encryptor.decrypt(userLogin.getPassword()).equals(userLoginDTO.getPassword())) {
                    return UserLoginDTO.builder()
                            .email(userLogin.getEmail())
                            .userName(userLogin.getUserName())
                            .userId(userLogin.getUserId())
                            .build();
                }
                throw new UserException("Invalid username or password.");
            }
            throw new UserException("Invalid username or password.");
        }
        return null;
    }

    @Override
    public UserLoginDTO loginGoogle(UserLoginDTO userLoginDTO) throws UserException {
        if (userLoginDTO != null) {
            UserLogin userLogin = userLoginRepository.getGoogleUserLogin(userLoginDTO.getEmail());
            if (userLogin != null) {
                return UserLoginDTO.builder()
                        .email(userLogin.getEmail())
                        .userName(userLogin.getUserName())
                        .userId(userLogin.getUserId())
                        .build();
            }
            throw new UserException("Not registered with Google Login.");
        }
        return null;
    }

    @Override
    public UserLogin signUp(UserLoginDTO userLoginDTO) throws UserException {
        if (userLoginDTO != null) {
            List<UserLogin> existingUsers = userLoginRepository.getExistingUserByEmail(userLoginDTO.getEmail());
            if (existingUsers.size() == 0) {
                UserLogin createdUser = UserLogin.builder()
                        .email(userLoginDTO.getEmail())
                        .password(encryptor.encrypt(userLoginDTO.getPassword()))
                        .isGoogle(false)
                        .userId(userLoginRepository.getMaxUserId() == null ? 0 : userLoginRepository.getMaxUserId() + 1)
                        .userName(userLoginDTO.getUserName())
                        .build();

                return userLoginRepository.save(createdUser);
            }
            for (UserLogin user : existingUsers) {
                if (!user.getIsGoogle()) {
                    throw new UserException("User with this email is already registered.");
                }
            }
            UserLogin createdUser = UserLogin.builder()
                    .email(userLoginDTO.getEmail())
                    .isGoogle(false)
                    .userId(userLoginRepository.getGoogleUserLogin(userLoginDTO.getEmail()).getUserId())
                    .userName(userLoginDTO.getUserName())
                    .build();

            return userLoginRepository.save(createdUser);
        }
        return null;
    }

    @Override
    public UserLogin signUpGoogle(UserLoginDTO userLoginDTO) throws UserException {
        if (userLoginDTO != null) {
            List<UserLogin> existingUsers = userLoginRepository.getExistingUserByEmail(userLoginDTO.getEmail());
            if (existingUsers.size() == 0) {
                UserLogin createdUser = UserLogin.builder()
                        .email(userLoginDTO.getEmail())
                        .isGoogle(true)
                        .userId(userLoginRepository.getMaxUserId() == null ? 0 : userLoginRepository.getMaxUserId() + 1)
                        .userName(userLoginDTO.getUserName())
                        .build();

                return userLoginRepository.save(createdUser);
            }
            for (UserLogin user : existingUsers) {
                if (user.getIsGoogle()) {
                    throw new UserException("User with this email is already registered.");
                }
            }
            UserLogin createdUser = UserLogin.builder()
                    .email(userLoginDTO.getEmail())
                    .isGoogle(true)
                    .userId(userLoginRepository.getNonGoogleUserLogin(userLoginDTO.getEmail()).getUserId())
                    .userName(userLoginDTO.getUserName())
                    .build();

            return userLoginRepository.save(createdUser);
        }
        return null;
    }

}
