package com.example.leavemanagementsystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekendDTO {
    private Long holidayId;
    private String startDate;
    private String description;
    private int status;
}
