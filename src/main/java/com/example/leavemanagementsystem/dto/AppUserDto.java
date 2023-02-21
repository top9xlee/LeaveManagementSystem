package com.example.leavemanagementsystem.dto;


import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.valid.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordsEqualConstraint(message = "Mật khẩu không trùng khớp")
public class AppUserDto {
    private Long userId;
    private String userName;
    private String encrytedPassword;
    private String newPassword;
    private String changePw;
    private String repassword;
    private String matchpassword;
    private String email;
    private Department departmentId;
    private String fullName;
    private String jobName;
    private Long roleId;
    private int status;


    private String phoneNumber;

    public UserEntity mapperAppUser() {

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setDepartment(departmentId);
        user.setFullName(fullName);
        user.setUserName(userName);
        user.setJobName(jobName);
        user.setEncrytedPassword(encrytedPassword);
        user.setRole(roleId);
        user.setStatus(status);
        user.setPhoneNumber(phoneNumber);

        return user;
    }
}
