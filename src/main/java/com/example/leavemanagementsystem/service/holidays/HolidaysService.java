package com.example.leavemanagementsystem.service.holidays;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;

import java.time.*;
import java.util.*;

public interface HolidaysService {
    void save(Holiday holiday);

    List<Holiday> getlistHolidays();

    List<Holiday> getlistHolidaysByYear(Long id);

    //    Holiday update(Holiday holiday);
    void deleteHoliday(Long id);

    List<Holiday> getListHolidaysByMonth(Long year, Long month);

    List<Holiday> getListHolidaysBetweenDays(LocalDate startDate, LocalDate endDate);

    int countHolidaysBetweenDays(LocalDate startDate, LocalDate endDate);

    List<Date> getListWeekendByMonth(Long year, Long month);

    List<Date> getListHolidayssByMonth(Long year, Long month);

    PageResponse getAllHoliday(int limit, int page, int status);

}
