package com.example.leavemanagementsystem.model;

import lombok.*;
import org.springframework.format.annotation.*;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holidayId;
    @Column(name = "Start_Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Column(name = "End_Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    @Column(name = "Description")
    private String description;
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "status")
    private int status;
}
