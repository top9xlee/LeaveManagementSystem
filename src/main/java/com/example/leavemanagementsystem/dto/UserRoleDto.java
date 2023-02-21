package com.example.leavemanagementsystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {
    private Long id;
    private Long userId;
    private String roleName;
}
