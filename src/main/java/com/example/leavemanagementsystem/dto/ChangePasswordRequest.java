package com.example.leavemanagementsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {
    private String userId;
    private String password;
    private String rePassword;

}
