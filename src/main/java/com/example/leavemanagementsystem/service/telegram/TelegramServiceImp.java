package com.example.leavemanagementsystem.service.telegram;

import com.example.leavemanagementsystem.model.Absence;
import com.example.leavemanagementsystem.model.Telegram;
import com.example.leavemanagementsystem.model.UserEntity;
import com.example.leavemanagementsystem.repository.TelegramRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Service


public class TelegramServiceImp implements TelegramService{

    @Autowired
    TelegramRepository telegramRepository;


    @Override
    public void deleteTelegram(Long id) {
        Telegram telegram = telegramRepository.findById(id).get();
        telegramRepository.delete(telegram);
    }
    @Override
    public Telegram update(Telegram telegram) {
        Telegram telegram1 = telegramRepository.findById(telegram.getTelegramId()).get();
        telegram1.setTelegramName(telegram.getTelegramName());
        telegram1.setApiToken(telegram.getApiToken());
        telegram1.setChatId(telegram.getChatId());
        return telegramRepository.save(telegram1);

    }
    @Override
    public void save(Telegram telegram) {
        telegramRepository.save(telegram);
    }

    public  void sendToTelegram(UserEntity user, Absence absenceResponse1, String apiToken, String chatId) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime startDate1 = LocalDateTime.ofInstant(absenceResponse1.getStartDate().toInstant(), ZoneId.systemDefault());
        LocalDateTime endDate1 = LocalDateTime.ofInstant(absenceResponse1.getEndDate().toInstant(), ZoneId.systemDefault());
        String startDate = startDate1.toLocalDate().format(formatter);
        String endDate = endDate1.toLocalDate().format(formatter);
        StringBuilder text = new StringBuilder();
        text.append("B???N C?? M???T TH??NG B??O NGH??? PH??P M???I! %0A");
        text.append("H??? v?? t??n: " + user.getFullName() + "%0A");
        text.append("Ch???c v???: " + user.getJobName()+ "%0A");
        text.append("Ph??ng ban: " + user.getDepartment().getDepartmentName() + "%0A");
        text.append("Xin ngh??? ph??p: T??? " + startDate1.getHour() +"h" + startDate1.getMinute() + " ng??y " + startDate + " ?????n " + endDate1.getHour() + "h" + endDate1.getMinute() + " ng??y " + endDate +"%0A");
        text.append("S??? ng??y: " + absenceResponse1.getDayOff() +" %0A");
        text.append("L?? do: " + absenceResponse1.getDescription() +" %0A");
        text.append("Link: https://tms.vconnex.vn/department");

        urlString = String.format(urlString, apiToken, chatId, text);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
