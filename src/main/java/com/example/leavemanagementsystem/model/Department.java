package com.example.leavemanagementsystem.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @NotNull
    @Column(name = "department_Name")
    private String departmentName;
    @NotNull
    @Column(name = "head_Department_Id")
    @JoinColumn(name = "user", referencedColumnName = "userId")
    private Long headId;
    @Column(name = "status")
    private int status;


}
