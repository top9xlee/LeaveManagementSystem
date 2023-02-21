package com.example.leavemanagementsystem.dto;

import lombok.*;
import org.springframework.format.annotation.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsenceRequest {
    private Long id;
    private String title;
    private String description;
    private Long personInCharge;
    private int type;
    private int typeFromDate;
    private int typeToDate;
    private int page;
    private int NumOfList;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

}
