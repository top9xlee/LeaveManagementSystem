package com.example.leavemanagementsystem.service.userRole;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.mapper.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRoleDto> getListRoleOfUser(Long id) {
        return userRoleRepository.getListRoleOfUser(id).stream().map(roleMapper::maptoDto).collect(Collectors.toList());
    }
}
