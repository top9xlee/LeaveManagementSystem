package com.example.leavemanagementsystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userId;
    private String fullName;
    private String email;
    private String jobName;
    private String departmentName;
    private boolean isHeadDepartment;
    private int numberOfPosts;
    private int numberOfDayOffs;
    private int numberOfNotEnable;
    private int numberOfEnabled;
    private int numberOfRejected;
    private int numberOfRejectedOfDepartment;

}
