package com.example.leavemanagementsystem.mapper;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.absenceRepository.*;
import com.example.leavemanagementsystem.repository.user.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.*;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AbsenceRepository absenceRepository;
//    @Mapping(target =("numberOfPosts"),expression = "java(getNumberOfPost(UserEntity))")
//    @Mapping(target =("numberOfDayOffs"),expression = "java(getDayoffs(UserEntity))")
//    @Mapping(target =("numberOfNotEnable"),expression = "java(getPostNotEnable(UserEntity))")
//    @Mapping(target =("numberOfEnabled"),expression = "java(getPostEnabled(UserEntity))")
//    @Mapping(target =("numberOfRejected"),expression = "java(getPostRejected(UserEntity))")

    public abstract UserResponse maptoDto(UserEntity UserEntity);
//    int getNumberOfPost(UserEntity userEntity){
//        return absenceRepository.findAllByUserId(userEntity.getUserId()).size();
//    }
//    int getDayoffs(UserEntity userEntity){
//        return userEntity.getListDayOff().size();
//    }
//    int getPostNotEnable(UserEntity userEntity){
//        return absenceRepository.findAllByStatusAndUserId(0,userEntity.getUserId()).size();
//    }
//    int getPostEnabled(UserEntity userEntity){
//        return absenceRepository.findAllByStatusAndUserId(1,userEntity.getUserId()).size();
//    }
//    int getPostRejected(UserEntity userEntity){
//        return absenceRepository.findAllByStatusAndUserId(2,userEntity.getUserId()).size();
//    }
}
