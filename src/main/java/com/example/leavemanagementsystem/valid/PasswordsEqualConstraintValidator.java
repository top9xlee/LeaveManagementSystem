package com.example.leavemanagementsystem.valid;


import com.example.leavemanagementsystem.dto.*;

import javax.validation.*;

public class PasswordsEqualConstraintValidator implements
        ConstraintValidator<PasswordsEqualConstraint, Object> {

    @Override
    public void initialize(PasswordsEqualConstraint arg0) {
    }

    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
        AppUserDto appUser = (AppUserDto) candidate;
        return appUser.getEncrytedPassword().equals(appUser.getRepassword());
    }
}
