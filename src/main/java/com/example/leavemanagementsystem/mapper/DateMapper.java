package com.example.leavemanagementsystem.mapper;


import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.utils.*;
import org.mapstruct.*;

import java.time.*;

@Mapper(componentModel = "spring")
public abstract class DateMapper {

    @Mapping(target = "type", source = "type")
    @Mapping(target = "date", expression = "java(getLocalDateFromDateResponse(dateResponse))")
    @Mapping(target = "hour", expression = "java(getHour(dateResponse))")
    public abstract LocalDateResponse maptoDto(DateResponse dateResponse);

    @Mapping(target = "hour", source = "hour")
    @Mapping(target = "date", expression = "java(getDate(localDateResponse))")
    public abstract DateUnit maptoDateUnit(LocalDateResponse localDateResponse);


    LocalDate getLocalDateFromDateResponse(DateResponse dateResponse) {
        return dateResponse.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    int getHour(DateResponse dateResponse) {
        LocalDateTime a = dateResponse.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return a.getHour();
    }

    int getDate(LocalDateResponse localDateResponse) {
        return localDateResponse.getDate().getDayOfMonth();
    }

}
