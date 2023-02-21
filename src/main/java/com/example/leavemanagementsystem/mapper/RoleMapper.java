package com.example.leavemanagementsystem.mapper;


import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.*;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    AppRoleRepository appRoleRepository;

    @Mapping(target = "roleName", source = "appRole.roleName")
    @Mapping(target = "userId", source = "user.userId")
    public abstract UserRoleDto maptoDto(UserRole userRole);

}
