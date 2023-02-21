package com.example.leavemanagementsystem.repository.absenceRepository;

import com.example.leavemanagementsystem.dto.*;

import java.time.*;
import java.util.*;

public interface AbsenceRepositoryCustom {
    List<AbsenceResponse> findAllByUserId(Long id, Integer limit, Integer offset);

    List<AbsenceResponse> findAllAbsenceByDepaIdAndStatus(Long status, Long id, Integer limit, Integer offset);

    List<AbsenceResponse> findAllAbsenceByDepaId(Long id, Integer limit, Integer offset);

    List<AbsenceResponse> getListAbsenceResponseByMonth(LocalDate date1, LocalDate date2);

    List<WordResponse> findByAbsenceId(Long id);

    List<WordResponse> getListWordResponseByMonth(LocalDate date1, LocalDate date2);

}

