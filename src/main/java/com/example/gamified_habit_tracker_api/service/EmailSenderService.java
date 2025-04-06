package com.example.gamified_habit_tracker_api.service;

public interface EmailSenderService {
    void sendEmail(String toEmail, int subject);
}
