package com.example.leavemanagementsystem.service.department;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;

import java.util.*;

public interface DepartmentService {
    void save(Department department);

    List<DepartmentResponse> getListDepartment();

    List<UserEntity> getUsersByDepartment(Long id);

    List<UserResponse> getUsersByDepartmentId(Long id);

    Department update(Department department);

    DepartmentResponse getDepartment(Long id);

    DepartmentResponse getDepartmentOfManager(Long id);

    void deleteDepartment(Long id);

    PageResponse getAllAbsenceByDepaId(int limit, int page, Department department);

    PageResponse getAllAbsenceByDepaIdAndStatus(long status, int limit, int page, Department department);
}
