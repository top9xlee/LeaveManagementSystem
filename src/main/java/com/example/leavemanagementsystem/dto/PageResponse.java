package com.example.leavemanagementsystem.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageResponse extends Response {
    long count;
}
