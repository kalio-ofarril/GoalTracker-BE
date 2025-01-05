package com.goaltracker.GoalTracker.Services;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmai(String message);
    void sendSimpleMessage(String to, String subject, String text) throws MessagingException, UnsupportedEncodingException;
}
