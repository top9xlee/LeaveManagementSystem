package com.example.leavemanagementsystem.repository.user;

import com.example.leavemanagementsystem.model.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {

    List<UserEntity> findAllByDepartment(Department department);

    UserEntity findByUserId(Long id);

    @Query("select m from UserEntity m  where m.verificationCode = ?1 and m.status = 1 ")
    UserEntity findByVerificationCode(String code);

    UserEntity findByEmail(String email);

    UserEntity findByUserName(String name);

    List<UserEntity> findByDepartment_DepartmentId(Long id);

    @Query("select m from UserEntity m  where m.role = ?1 and m.status = 1 ")
    List<UserEntity> findByRole(Long role);

    @Query("select count(m) from UserEntity m  where m.status = 1")
    int getAllUserEntityEnable();

    @Query("select m from UserEntity m  where NOT m.role = 1 ")
    List<UserEntity> findAllUser();

    Page<UserEntity> findAllByStatus(int i, Pageable pageable);

    List<UserEntity> findAllByStatus(int i);

}
