package com.example.leavemanagementsystem.utils;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import org.apache.commons.lang3.time.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class Utils {
    public static float countBetweenDate1(Date fromDate, Date toDate) {
        long dateBeforeInMs = fromDate.getTime();

        long dateAfterInMs = toDate.getTime();

        long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);

        float daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        return daysDiff;
    }

    public static long countBetweenDate(Date fromDate, Date toDate) {
        long dateBeforeInMs = fromDate.getTime();

        Date tomorrowToDate = new Date(toDate.getTime() + (1000 * 60 * 60 * 24));
        long dateAfterInMs = tomorrowToDate.getTime();

        long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);

        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        return daysDiff;
    }
// shiftFrom == 1 ? true : false, shiftTo == 1 ? false : true
    public static float countBetweenDate(Date fromDate, Date toDate, boolean isStartMorning, boolean isEndAfternoon) {
        long dateBeforeInMs = fromDate.getTime();

        Date tomorrowToDate = new Date(toDate.getTime() + (1000 * 60 * 60 * 24));
        long dateAfterInMs = tomorrowToDate.getTime();

        long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);

        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        if ((isStartMorning && isEndAfternoon) || (!isStartMorning && !isEndAfternoon)) return daysDiff - 0.5F;
        if (isStartMorning && !isEndAfternoon) return daysDiff;
        return daysDiff -1;
    }

    public static List<Date> getDaysBetweenDates(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        Date tomorrowEndDate = new Date(endDate.getTime() + (1000 * 60 * 60 * 24));

        while (calendar.getTime().before(tomorrowEndDate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static List<DateResponse> getDaysAndTypeBetweenDates(Absence absence) {
        List<DateResponse> dates = new ArrayList<>();
        Calendar startDate = new GregorianCalendar();
        startDate.setTime(absence.getStartDate());
        Calendar endDate = new GregorianCalendar();
        endDate.setTime(absence.getEndDate());
        Date tomorrowEndDate = new Date(absence.getEndDate().getTime() + (1000 * 60 * 60 * 24));

        while (startDate.getTime().before(tomorrowEndDate)) {
            Date result = startDate.getTime();
            boolean isSameLastDay = DateUtils.isSameDay(result, absence.getEndDate());
            boolean isSameFirstDay = DateUtils.isSameDay(result, absence.getStartDate());
            DateResponse dateResponse = new DateResponse();
            if (isSameLastDay == true) {
                dateResponse.setDate(endDate.getTime());
                dateResponse.setType(absence.getType());
                dates.add(dateResponse);
                startDate.add(Calendar.DATE, 1);
            } else if (isSameFirstDay == true) {
                dateResponse.setDate(startDate.getTime());
                dateResponse.setType(absence.getType());
                dates.add(dateResponse);
                startDate.add(Calendar.DATE, 1);
            } else {
                LocalDateTime localDateTime = result.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime localDate = LocalDateTime
                        .of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 17, 30);
                Date date = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
                dateResponse.setDate(date);
                dateResponse.setType(absence.getType());
                dates.add(dateResponse);
                startDate.add(Calendar.DATE, 1);
            }
        }
        return dates;
    }


    public static boolean checkNaturalNumber(float a) {
        double n = a;
        // nếu là số thực thì trả về false
        return Math.floor(n) == n; // nếu là số nguyên thì trả về true
    }

    public static Response createResponse(String message, int code, Object obj) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setObj(obj);
        return response;
    }

    public static PageResponse createPageResponse(long count, List list) {
        PageResponse response = new PageResponse();
        response.setCode(1);
        response.setObj(list);
        response.setCount(count);
        return response;
    }


}
