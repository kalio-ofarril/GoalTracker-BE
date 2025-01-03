package com.goaltracker.GoalTracker.Data.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.goaltracker.GoalTracker.Data.Entities.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

    @Query(value = "SELECT * FROM user_login WHERE is_google = 0 AND email = :email AND password = :password;", nativeQuery = true)
    public UserLogin getUserLogin(@Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT * FROM user_login WHERE is_google = 0 AND email = :email;", nativeQuery = true)
    public UserLogin getNonGoogleUserLogin(@Param("email") String email);

    @Query(value = "SELECT * FROM user_login WHERE is_google = 1 AND email = :email;", nativeQuery = true)
    public UserLogin getGoogleUserLogin(@Param("email") String email);

    @Query(value = "SELECT * FROM user_login WHERE email = :email;", nativeQuery = true)
    public List<UserLogin> getExistingUserByEmail(@Param("email") String email);

    @Query(value = "SELECT MAX(user_id) FROM user_login;", nativeQuery = true)
    public Long getMaxUserId();

}
