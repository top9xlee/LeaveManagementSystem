package com.example.leavemanagementsystem.utils;

import com.example.leavemanagementsystem.dto.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewOutput {
    private int page;
    private int totalPage;
    private Response response;
    private int previousPage;
    private int nextPage;
}
