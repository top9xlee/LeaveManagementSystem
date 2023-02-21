package com.example.leavemanagementsystem.repository;

import com.example.leavemanagementsystem.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentId(Long id);

    Department findAllByHeadId(Long id);

    List<Department> findAllByStatus(int status);

    Department findByHeadId(Long id);

//    @Query(value = "SELECT * FROM department as d WHERE not d.department_id = 1", nativeQuery = true)
//    List<Department> getDepartmentMannager();
}
