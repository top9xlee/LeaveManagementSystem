package com.example.leavemanagementsystem.dto;

import com.example.leavemanagementsystem.model.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityDto {

    private Long userId;
    private String fullName;
    private String email;
    private String encrytedPassword;
    private String verificationCode;
    private String userName;
    private Department department;
    private String jobName;
    private Long role;
    private int status;
    private AppRole appRole;
    private String phoneNumber;
    private int page;
    private int numOfList;


}
