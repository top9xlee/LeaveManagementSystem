package com.example.leavemanagementsystem.service.userRole;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;

import java.util.*;

public interface UserRoleService {
    void save(UserRole userRole);

    List<UserRoleDto> getListRoleOfUser(Long id);
}
