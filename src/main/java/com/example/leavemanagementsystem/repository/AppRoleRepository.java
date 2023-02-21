package com.example.leavemanagementsystem.repository;

import com.example.leavemanagementsystem.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    List<AppRole> findAll();

    AppRole findByRoleId(Long id);

}
