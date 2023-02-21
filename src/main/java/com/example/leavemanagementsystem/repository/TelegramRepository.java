package com.example.leavemanagementsystem.repository;

import com.example.leavemanagementsystem.model.Telegram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramRepository extends JpaRepository<Telegram, Long> {

}
