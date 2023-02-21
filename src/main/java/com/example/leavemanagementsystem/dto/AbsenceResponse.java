package com.example.leavemanagementsystem.dto;

import com.example.leavemanagementsystem.model.*;
import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsenceResponse {
    private Long absenceId;
    private UserEntity userEntity;
    private Long userId;
    private String userName;
    private String description;
    private int status;
    private String statusAbsence;
    private Long enableId;
    private Date startDate;
    private Date endDate;
    private float dayOff;
    private Department department;
    private Long departmentId;
    private String departmentName;
    private Date createDate;
    private Date enableDate;
    private String postUser;
    private String title;
    private String note;
    private String typeLeave;
    private String jobName;
    private String headName;
    private String fullNameOfDepartment;//actorName
    private String applicationReviewer;//người duyệt
    private int type;
    private Long absenceBy;
    private String phoneNumber;
    private Long personInCharge;
    private String namePersonInCharge;
}
