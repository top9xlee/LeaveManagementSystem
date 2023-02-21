package com.example.leavemanagementsystem.service.appRole;

import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AppRoleServiceImpl implements AppRoleService {
    @Autowired
    AppRoleRepository appRoleRepository;

    @Override
    public List<AppRole> getListRole() {
        return appRoleRepository.findAll();
    }
}
