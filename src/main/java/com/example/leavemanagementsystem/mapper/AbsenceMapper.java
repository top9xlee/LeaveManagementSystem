package com.example.leavemanagementsystem.mapper;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.user.*;
import com.example.leavemanagementsystem.service.department.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.*;

@Mapper(componentModel = "spring")
public abstract class AbsenceMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentService departmentService;

    @Mapping(target = "fullNameOfDepartment", expression = "java(getFullNameOfDepartment(absence))")
    public abstract AbsenceResponse maptoDto1(Absence absence);

    @Mapping(target = "namePersonInCharge", expression = "java(getFullNameOfPersonInCharge(absenceResponse))")
    @Mapping(target = "applicationReviewer", expression = "java(getApplicationReviewer(absenceResponse))")
    public abstract AbsenceResponse maptoDto2(AbsenceResponse absenceResponse);

    @Mapping(target = "headName", expression = "java(getHeadName1(wordResponse))")
    @Mapping(target = "jobNamePersonInCharge", expression = "java(getJobOfPersonInCharge(wordResponse))")
    @Mapping(target = "namePersonInCharge", expression = "java(getFullNameOfPersonInCharge1(wordResponse))")
    @Mapping(target = "absenceResponse", source = "absenceResponse")
    public abstract WordDto mapToWord(WordResponse wordResponse);

    String getApplicationReviewer(AbsenceResponse absenceResponse) {
        Long a = absenceResponse.getAbsenceBy();
        if (a == null || a == 0) {
            return "";
        }
        UserEntity userEntity = userRepository.findByUserId(a);
        return userEntity.getFullName();
    }

    String getHeadName(AbsenceResponse absence) {
        DepartmentResponse departmentResponse = departmentService.getDepartment(absence.getDepartmentId());
        return departmentResponse.getHeadName();
    }

    String getHeadName1(WordResponse absence) {
        UserEntity userEntity = userRepository.findByUserId(absence.getUserId());
        DepartmentResponse departmentResponse =
                departmentService.getDepartment(userEntity.getDepartment().getDepartmentId());
        return departmentResponse.getHeadName();
    }

    String getFullNameOfDepartment(Absence absence) {
        UserEntity userEntity = userRepository.findByUserId(absence.getUserId());
        DepartmentResponse departmentResponse =
                departmentService.getDepartment(userEntity.getDepartment().getDepartmentId());
        return departmentResponse.getHeadName();
    }

    String getFullNameOfPersonInCharge(AbsenceResponse absence) {
        UserEntity userEntity = userRepository.findByUserId(absence.getPersonInCharge());
        return userEntity.getFullName();
    }

    String getFullNameOfPersonInCharge1(WordResponse absence) {
        UserEntity userEntity = userRepository.findByUserId(absence.getPersonInCharge());
        return userEntity.getFullName();
    }

    String getJobOfPersonInCharge(WordResponse absence) {
        UserEntity userEntity = userRepository.findByUserId(absence.getPersonInCharge());
        return userEntity.getJobName();
    }
}