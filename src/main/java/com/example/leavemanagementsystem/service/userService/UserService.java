package com.example.leavemanagementsystem.service.userService;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import org.springframework.data.domain.*;

import java.util.*;

public interface UserService {
    void createAppUser(UserEntity user);

    boolean updatePw(AppUserDto appUserDto);

    UserEntity findUserById(Long id);

    UserEntity findByEmail(String username);

    Boolean resetPassword(UserEntity appUser, String newPass);

    UserEntity findByVerificationCode(String code);

    Boolean verify(String code);

    UserEntity getInfoVerify(String code);

    void setVerify(Long id);

    List<UserEntity> getListUserByDepartment(Long id);

    PageResponse getAllUserEntityEnable(int limitPageUser, int page, Sort sort);

    Response deleteUserEntity(Long id);

    Response updateUserEntity(UserEntityDto userEntityDto, Long id);

    boolean checkUserName(AppUserDto appUserDto);

    boolean checkEmail(AppUserDto appUserDto);

    boolean checkPhoneNumber(AppUserDto appUserDto);

    int totalItem();

    PageResponse getAllByUserId(int limitPageUser, int page, UserEntity user);

}

