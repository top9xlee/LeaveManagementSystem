package com.example.leavemanagementsystem.dto;

import lombok.*;

@Data
public class Response {
    private String message;
    private int code;
    private Object obj;
}
