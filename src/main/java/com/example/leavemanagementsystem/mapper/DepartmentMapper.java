package com.example.leavemanagementsystem.mapper;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.user.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.*;

@Mapper(componentModel = "spring")
public abstract class DepartmentMapper {
    @Autowired
    private UserRepository userRepository;

    @Mapping(target = "numberOfUser", expression = "java(getNumberOfUser(department))")
    @Mapping(target = "headName", expression = "java(getNameOfHeadDepartment(department))")
    @Mapping(target = "emailOfHeadDepartment", expression = "java(getEmailOfHeadDepartment(department))")
    public abstract DepartmentResponse maptoDto(Department department);

    int getNumberOfUser(Department department) {
        int getNumberOfUser = userRepository.findAllByDepartment(department).size();
        return getNumberOfUser;
    }

    String getNameOfHeadDepartment(Department department) {
        UserEntity userEntity = userRepository.findByUserId(department.getHeadId());
        return userEntity.getFullName();
    }

    String getEmailOfHeadDepartment(Department department) {
        UserEntity userEntity = userRepository.findByUserId(department.getHeadId());
        return userEntity.getEmail();
    }

}
