package com.example.leavemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "telegram")

public class Telegram {
    @Id
    @Column(name = "telegram_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long telegramId;

    @Column(name = "telegram_name")
    private String telegramName;

    @Column(name = "api_token")
    private String apiToken;

    @Column(name = "chat_id")
    private String chatId;
}
