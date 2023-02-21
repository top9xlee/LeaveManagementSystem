package com.example.leavemanagementsystem.dao;

import com.example.leavemanagementsystem.mapper.*;
import com.example.leavemanagementsystem.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.support.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import javax.sql.*;

//import project3.demo.mapper.AppUserMapper;
//import project3.demo.model.AppUser;

//import org.o7planning.sbsecurity.mapper.AppUserMapper;
//import org.o7planning.sbsecurity.model.AppUser;

@Repository
@Transactional
public class AppUserDAO extends JdbcDaoSupport {

    @Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public UserEntity findUserAccount(String userName) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = AppUserMapper.SQL + " where u.user_name = ? ";

        Object[] params = new Object[]{userName};
        AppUserMapper mapper = new AppUserMapper();
        try {
            UserEntity userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            int a = userInfo.getStatus();
            System.out.println(a);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}

