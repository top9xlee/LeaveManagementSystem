package com.example.leavemanagementsystem.service.holidays;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.*;
import com.example.leavemanagementsystem.utils.*;
import lombok.extern.log4j.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@Log4j
@Service
public class HolidaysServiceImpl implements HolidaysService {
    @Autowired
    HolidaysRepo holidaysRepo;

    @Override
    public void save(Holiday holiday) {
        holidaysRepo.save(holiday);
    }

    @Override
    public List<Holiday> getlistHolidays() {
        return holidaysRepo.findAll();
    }

    @Override
    public List<Holiday> getlistHolidaysByYear(Long id) {
        return null;
    }


    @Override
    public void deleteHoliday(Long id) {
        Holiday holiday = holidaysRepo.findByHolidayId(id);
        holidaysRepo.delete(holiday);
    }

    @Override
    public List<Holiday> getListHolidaysByMonth(Long year, Long month) {
        LocalDate localDate = LocalDate.of(year.intValue(), month.intValue(), 1);
//        localDate.getMonth();
        LocalDate lastDayOfMonthDate = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date1 = Date.from(lastDayOfMonthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Holiday> holidays = holidaysRepo.getListHolidayByMonth(date, date1);
        return holidays;
    }

    @Override
    public List<Holiday> getListHolidaysBetweenDays(LocalDate startDate, LocalDate endDate) {
//        localDate.getMonth();
        Date date = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date1 = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Holiday> holidays = holidaysRepo.getListHolidayByMonth(date, date1);
        return holidays;
    }

    @Override
    public int countHolidaysBetweenDays(LocalDate startDate, LocalDate endDate) {
        Date fromDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date toDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        int count = holidaysRepo.countHolidayByDate(fromDate, toDate);
        return count;
    }

    @Override
    public List<Date> getListWeekendByMonth(Long year, Long month) {
        LocalDate localDate = LocalDate.of(year.intValue(), month.intValue(), 1);
        List<Date> holidayLst = new ArrayList<>();
        LocalDate lastDayOfMonthDate = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
        Date firstDateOfMonth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateOfMonth = Date.from(lastDayOfMonthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Holiday> holidays = holidaysRepo.getListWeekendByMonth(firstDateOfMonth, endDateOfMonth);
        for (Holiday item : holidays) {
            List<Date> itemDates = Utils.getDaysBetweenDates(item.getStartDate(), item.getEndDate());
            for (Date itemDate : itemDates) {
                if (!holidayLst.contains(itemDate) && betweenInputDate(itemDate, firstDateOfMonth, endDateOfMonth))
                    holidayLst.add(itemDate);
            }
        }

        return holidayLst;

    }

    private boolean betweenInputDate(Date itemDate, Date startDate, Date endDate) {
        return itemDate.compareTo(startDate) >= 0 && itemDate.compareTo(endDate) <= 0;
    }

    @Override
    public List<Date> getListHolidayssByMonth(Long year, Long month) {
        LocalDate localDate = LocalDate.of(year.intValue(), month.intValue(), 1);
        List<Date> holidayLst = new ArrayList<>();
        LocalDate lastDayOfMonthDate = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
        Date firstDateOfMonth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateOfMonth = Date.from(lastDayOfMonthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Holiday> holidays = holidaysRepo.getListHolidaysByMonth(firstDateOfMonth, endDateOfMonth);

        List<Date> weekend = getListWeekendByMonth(year, month);
        Optional<List<Date>> weekendDay = Optional.of(weekend);
        Predicate<Date> isWeekend = date -> weekendDay.isPresent()
                && weekendDay.get().contains(date);

        for (Holiday item : holidays) {
            List<Date> itemDates = Utils.getDaysBetweenDates(item.getStartDate(), item.getEndDate());
            for (Date itemDate : itemDates) {
                if (!holidayLst.contains(itemDate) && betweenInputDate(itemDate, firstDateOfMonth, endDateOfMonth))
                    holidayLst.add(itemDate);
            }
        }
        List<Date> daysWorking = holidayLst.stream()
                .filter(isWeekend.negate())
                .collect(Collectors.toList());
        return daysWorking;
    }

    @Override
    public PageResponse getAllHoliday(int limit, int page, int status) {
        long count = 0;
        List<Holiday> list = new ArrayList<>();

        try {
            org.springframework.data.domain.Pageable pageable = PageRequest.of(page - 1, limit);
            count = holidaysRepo.countAllByStatusOrderByStartDateAsc(status);
            Page<Holiday> holidays = holidaysRepo.findAllByStatusOrderByStartDateAsc(status, pageable);
            for (Holiday item : holidays) {
                Holiday holiday = new Holiday();
                BeanUtils.copyProperties(item, holiday);
                list.add(holiday);
            }

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return Utils.createPageResponse(count, list);
    }
}
