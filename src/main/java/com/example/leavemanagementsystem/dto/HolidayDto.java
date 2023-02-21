package com.example.leavemanagementsystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolidayDto {
    private Long holidayId;
    private String startDate;
    private String endDate;
    private String description;
    private int status;
}
