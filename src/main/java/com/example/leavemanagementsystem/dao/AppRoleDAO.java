package com.example.leavemanagementsystem.dao;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.support.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import javax.sql.*;
import java.util.*;

@Repository
@Transactional
public class AppRoleDAO extends JdbcDaoSupport {

    @Autowired
    public AppRoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<String> getRoleNames(Long userId) {
        String sql = "Select r.Role_Name " //
                + " from user_role ur, app_role r " //
                + " where ur.Role_Id = r.Role_Id and ur.User_Id = ? ";

        Object[] params = new Object[]{userId};

        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

        return roles;
    }

}