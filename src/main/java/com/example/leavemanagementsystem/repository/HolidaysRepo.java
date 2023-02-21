package com.example.leavemanagementsystem.repository;

import com.example.leavemanagementsystem.model.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface HolidaysRepo extends JpaRepository<Holiday, Long> {
    Holiday findByHolidayId(Long id);

    List<Holiday> findAllByStatusOrderByStartDateAsc(int status);

    Page<Holiday> findAllByStatusOrderByStartDateAsc(int status, Pageable pageable);

    long countAllByStatusOrderByStartDateAsc(int status);

    @Query("select m from Holiday m where (m.startDate <= ?1 and m.endDate >= ?2) or (m.startDate >= ?1 and m.endDate >= ?2 and m.startDate <= ?2) or (m.startDate <= ?1 and m.endDate <= ?2 and m.endDate >= ?1) or (m.startDate >= ?1 and m.endDate <= ?2)")
    List<Holiday> getListHolidayByMonth(Date fromDate, Date toDate);

    @Query("select m from Holiday m where ((m.startDate <= ?1 and m.endDate >= ?2) or (m.startDate >= ?1 and m.endDate >= ?2 and m.startDate <= ?2) or (m.startDate <= ?1 and m.endDate <= ?2 and m.endDate >= ?1) or (m.startDate >= ?1 and m.endDate <= ?2)) and m.status = 1")
    List<Holiday> getListWeekendByMonth(Date fromDate, Date toDate);

    @Query("select m from Holiday m where ((m.startDate <= ?1 and m.endDate >= ?2) or (m.startDate >= ?1 and m.endDate >= ?2 and m.startDate <= ?2) or (m.startDate <= ?1 and m.endDate <= ?2 and m.endDate >= ?1) or (m.startDate >= ?1 and m.endDate <= ?2)) and m.status = 2")
    List<Holiday> getListHolidaysByMonth(Date fromDate, Date toDate);

    @Query("select count(m) from Holiday m where m.startDate >= ?1 and  m.endDate <= ?2")
    int countHolidayByDate(Date fromDate, Date toDate);


}
