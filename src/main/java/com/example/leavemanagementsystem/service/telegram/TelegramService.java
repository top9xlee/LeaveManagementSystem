package com.example.leavemanagementsystem.service.telegram;

import com.example.leavemanagementsystem.model.Absence;
import com.example.leavemanagementsystem.model.Telegram;
import com.example.leavemanagementsystem.model.UserEntity;

public interface TelegramService {
    void deleteTelegram(Long id);

    Telegram update(Telegram telegram);

    void save(Telegram telegram);

    void sendToTelegram(UserEntity user, Absence absenceResponse1, String apiToken, String chatId);
}
