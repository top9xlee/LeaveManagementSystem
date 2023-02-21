package com.example.leavemanagementsystem.repository;

import com.example.leavemanagementsystem.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(value = "select m from UserRole m join UserEntity n on m.user.userId = n.userId join AppRole p on m.appRole.roleId = p.roleId where p.roleName = ?1",
            nativeQuery = false)
    List<UserEntity> getListUserRole(String name);

    @Query("select m from UserRole m where m.user.userId= ?1")
    List<UserRole> getListRoleOfUser(Long id);

    @Query("select m from UserRole m where m.user.userId= ?1 and m.appRole.roleId=?2")
    UserRole getByUserAndRole(Long i, Long j);
//    UserRole findAllByUserAndAppRole(Long i, Long j);


}
