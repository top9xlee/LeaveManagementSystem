package com.example.leavemanagementsystem.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.format.annotation.*;

import javax.persistence.*;
import java.sql.*;
import java.util.Date;

import static javax.persistence.FetchType.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long absenceId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user", referencedColumnName = "userId")
    private UserEntity userEntity;
    @Column(name = "User_Id")
    private Long userId;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "Status")
    private int status;
    @Column(name = "AbsenceBy")
    private Long absenceBy;
    @Column(name = "Start_Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "End_Date")
    private Date endDate;
    @Column(name = "Date_Off")
    private float dayOff;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "department_Id", insertable = false, updatable = false)
    private Department department;
    @Column(name = "department_Id")
    private Long depaId;
    @Column(name = "create_date")
    private Timestamp createDate;
    @Column(name = "Enable_Date")
    private Date enableDate;
    @Column(name = "Type")
    private int type;
    @Column(name = "AbsenceResponse")
    private String absenceResponse;
    @Column(name = "person_in_charge")
    private Long personInCharge;

}
