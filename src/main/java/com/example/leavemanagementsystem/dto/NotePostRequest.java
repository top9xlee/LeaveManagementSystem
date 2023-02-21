package com.example.leavemanagementsystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotePostRequest {
    private Long absenceId;
    private String description;
    private int type;
}
