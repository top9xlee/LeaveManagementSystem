package com.example.leavemanagementsystem.dto;

import com.example.leavemanagementsystem.model.*;
import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportExcelDto {
    List<Absence> excelDto;
}
