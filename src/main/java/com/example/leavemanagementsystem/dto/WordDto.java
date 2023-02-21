package com.example.leavemanagementsystem.dto;

import com.example.leavemanagementsystem.model.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WordDto {
    private Long absenceId;
    private UserEntity userEntity;
    private Long userId;
    private String userName;
    private String description;
    private int status;
    private String statusAbsence;
    private Long enableId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private float dayOff;
    private Department department;
    private Long departmentId;
    private String departmentName;
    private LocalDateTime createDate;
    private Date enableDate;
    private String postUser;
    private String title;
    private String note;
    private int type;
    private String jobName;
    private String headName;
    private String fullNameOfDepartment;//actorName
    private String phoneNumber;
    private String namePersonInCharge;
    private String jobNamePersonInCharge;
    private String absenceResponse;

}
