package com.example.leavemanagementsystem.dto;

import lombok.*;

import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalDateResponse {
    private LocalDate date;
    private int hour;
    private int type;
}
