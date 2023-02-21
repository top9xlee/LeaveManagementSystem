package com.example.leavemanagementsystem.service.absenceService;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import org.springframework.data.domain.*;

import javax.mail.*;
import java.io.*;
import java.util.*;

public interface AbsenceService {
    void save(AbsenceRequest absence, UserEntity userEntity) throws MessagingException, UnsupportedEncodingException;

    Boolean update(AbsenceRequest absence);

    void enablePost(Long id, UserEntity userId, String note) throws MessagingException, UnsupportedEncodingException;

    void rejectPost(Long id, Long userId, String note);

    boolean deleteAbsence(Long id, Long userId);

    void hideAbsence(Absence absence);

    List<Absence> getListAbsenceEnabledByMonth(Long year, Long month);

    List<Absence> getListPostNonEnable();

    List<Absence> getListPostEnabled();

    List<Absence> getListPostNonEnableByDepartmentId(Long id);

    List<AbsenceResponse> getListPostByDepartmentId(Long id);

    List<AbsenceResponse> getListPostResponseNonEnableByDepartmentId(Long id);

    List<AbsenceResponse> getListPostResponseEnabledByDepartmentId(Long id);

    List<AbsenceResponse> getListPostResponseRejectedDepartmentId(Long id);

    List<AbsenceResponse> getListPostEnabled1();

    List<AbsenceResponse> getListPostResponseByUser(Long id);

    void deletePost(Long id);

    List<AbsenceResponse> getListAbsenceResponseByMonth(Long year, Long month);

    List<Absence> getListPostByDay(String day1, String day2);

    Float countLeaveDay(Date startDate, Date endDate, int shiftFrom, int shiftTo);

    List<Date> getWorksDayByUserId(Long year, Long month, Long userId);

    List<List<Date>> getWorksDay(Long year, Long month);

    List<Date> getListHoliday(Long year, Long month);

    List<List<DateResponse>> getListAbsenceByTypeAndMonth(Long year, Long month);

    int countByStatusAndDepaId(int status, Long departmentId);

    Page<Absence> getListPageAbsenceByStatus(Pageable pageable, int status);


    WordDto getWordAbsence(Long id);

    List<WordDto> getListWordAbsence(Long year, Long month);

    Float countDay(AbsenceRequest absenceRequest);

//    List<AbsenceResponse> findAllAbsenceByDepaIdAndStatus
}
