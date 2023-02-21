package com.example.leavemanagementsystem.mapper;


import com.example.leavemanagementsystem.model.*;
import org.springframework.jdbc.core.*;

import java.sql.*;

public class AppUserMapper implements RowMapper<UserEntity> {

    public static final String BASE_SQL //
            = "Select u.user_id, u.user_name, u.encryted_password From user u ";
    public static final String SQL
            = "SELECT * FROM user u";

    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long userId = rs.getLong("User_Id");
        String encrytedPassword = rs.getString("Encryted_Password");
        String userName = rs.getString("User_Name");

        int status = rs.getInt("status");

        return new UserEntity(userId, encrytedPassword, userName, status);

    }


}
