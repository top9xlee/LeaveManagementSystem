package com.example.leavemanagementsystem.service.userService;

import com.example.leavemanagementsystem.dao.*;
import com.example.leavemanagementsystem.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private AppRoleDAO appRoleDAO;

    @Override
    public MyUserPrincipal loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = this.appUserDAO.findUserAccount(userName);
        int check = user.getStatus();
        if (user == null || check == 0) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");

        }

//        System.out.println("Found User: " + user);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.appRoleDAO.getRoleNames(user.getUserId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

//        UserDetails userDetails = (UserDetails) new UserEntity(user.getUserId(),user.getEncrytedPassword(),user.getUserName());

        return new MyUserPrincipal(user, grantList);
//        return userDetails;
    }

}

