package com.example.leavemanagementsystem;

import com.example.leavemanagementsystem.service.absenceService.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;

@SpringBootTest
class LeaveManagementSystemApplicationTests {

    @Autowired
    AbsenceServiceImpl absenceService;

    @Test
    void contextLoads() {

    }



}
