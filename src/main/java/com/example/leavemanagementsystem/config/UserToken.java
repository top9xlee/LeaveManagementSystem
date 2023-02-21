package com.example.leavemanagementsystem.config;

import lombok.*;

@Data
public class UserToken {
    private String ip;
    private Long userId;
    private String userName;
}
