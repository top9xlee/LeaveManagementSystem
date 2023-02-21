package com.example.leavemanagementsystem.service.department;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.mapper.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.*;
import com.example.leavemanagementsystem.repository.absenceRepository.*;
import com.example.leavemanagementsystem.repository.user.*;
import com.example.leavemanagementsystem.utils.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AbsenceRepositoryCustom absenceRepositoryCustom;

    private final UserMapper userMapper;
    private final DepartmentMapper departmentMapper;

    public void save(Department department) {
        department.setStatus(1);
        departmentRepository.save(department);
    }

    @Override
    public List<DepartmentResponse> getListDepartment() {

        List<Department> department = departmentRepository.findAllByStatus(1);

        return department
                .stream()
                .map(departmentMapper::maptoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserEntity> getUsersByDepartment(Long id) {
        Department department = departmentRepository.findByDepartmentId(id);
        return userRepository.findAllByDepartment(department);
    }

    @Override
    public Department update(Department department) {
        Department department1 = departmentRepository.findByDepartmentId(department.getDepartmentId());
        department1.setDepartmentName(department.getDepartmentName());
        department1.setHeadId(department.getHeadId());
        department1.setStatus(1);
        return departmentRepository.save(department1);

    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findByDepartmentId(id);
        departmentRepository.delete(department);
    }

    @Override
    public DepartmentResponse getDepartment(Long id) {
        Department department = departmentRepository.findByDepartmentId(id);

        UserEntity userEntity = userRepository.findByUserId(department.getHeadId());
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartmentId(department.getDepartmentId());
        departmentResponse.setDepartmentName(department.getDepartmentName());
        departmentResponse.setHeadId(department.getHeadId());
        departmentResponse.setHeadName(userEntity.getFullName());

        List<UserEntity> userEntities = userRepository.findAllByDepartment(department);
        if (userEntities == null) {
            departmentResponse.setNumberOfUser(0);
        } else departmentResponse.setNumberOfUser(userEntities.size());

        return departmentResponse;
    }

    @Override
    public DepartmentResponse getDepartmentOfManager(Long id) {
        Department department = departmentRepository.findByHeadId(id);

        UserEntity userEntity = userRepository.findByUserId(department.getHeadId());
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartmentId(department.getDepartmentId());
        departmentResponse.setDepartmentName(department.getDepartmentName());
        departmentResponse.setHeadId(department.getHeadId());
        departmentResponse.setHeadName(userEntity.getFullName());

        List<UserEntity> userEntities = userRepository.findAllByDepartment(department);
        if (userEntities == null) {
            departmentResponse.setNumberOfUser(0);
        } else departmentResponse.setNumberOfUser(userEntities.size());

        return departmentResponse;
    }

    @Override
    public List<UserResponse> getUsersByDepartmentId(Long id) {
        Department department = departmentRepository.findByDepartmentId(id);
        return userRepository.findAllByDepartment(department).stream().map(userMapper::maptoDto)
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse getAllAbsenceByDepaId(int limit, int page, Department department) {
        long count = 0;
        List<AbsenceResponse> list = new ArrayList<>();

        try {
//            count = absenceRepositoryCustom.countAllByUserId(user.getUserId());
            count = absenceRepository.findAllAbsenceByDepaId(department.getDepartmentId());
            list = absenceRepositoryCustom
                    .findAllAbsenceByDepaId(department.getDepartmentId(), limit, (page - 1) * limit);
            for (AbsenceResponse absenceResponse : list){
                absenceResponse.setNamePersonInCharge(userRepository.findByUserId(absenceResponse.getPersonInCharge()).getFullName());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return Utils.createPageResponse(count, list);
    }

    @Override
    public PageResponse getAllAbsenceByDepaIdAndStatus(long status, int limit, int page, Department department) {
        long count = 0;
        List<AbsenceResponse> list = new ArrayList<>();

        try {
//            count = absenceRepositoryCustom.countAllByUserId(user.getUserId());
            count = absenceRepository.findAllAbsenceByDepaIdAndStatus(status, department.getDepartmentId());
            list = absenceRepositoryCustom
                    .findAllAbsenceByDepaIdAndStatus(status, department.getDepartmentId(), limit, (page - 1) * limit);
            for (AbsenceResponse absenceResponse : list){
                absenceResponse.setNamePersonInCharge(userRepository.findByUserId(absenceResponse.getPersonInCharge()).getFullName());
            }

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return Utils.createPageResponse(count, list);
    }

}
