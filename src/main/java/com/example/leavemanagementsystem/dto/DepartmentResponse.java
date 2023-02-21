package com.example.leavemanagementsystem.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
    private Long departmentId;
    private String departmentName;
    private Long headId;
    private String headName;
    private int numberOfUser;
    private String emailOfHeadDepartment;
}
