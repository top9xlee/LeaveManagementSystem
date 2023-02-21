package com.example.leavemanagementsystem.mapper;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class userMapUserDto {


    public abstract UserEntityDto maptoDto(UserEntity userEntity);

}
