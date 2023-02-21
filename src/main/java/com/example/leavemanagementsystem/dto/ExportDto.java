package com.example.leavemanagementsystem.dto;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportDto {
    private String data;
    private List<Integer> type;
    private String status;
}
