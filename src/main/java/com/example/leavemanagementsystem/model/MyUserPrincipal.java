package com.example.leavemanagementsystem.model;

import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

public class MyUserPrincipal implements UserDetails {
    private UserEntity userEntity;
    private List<GrantedAuthority> authorities;

    public MyUserPrincipal(UserEntity userEntity, List<GrantedAuthority> authorities) {
        this.userEntity = userEntity;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getEncrytedPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUserName();
    }

//    @Override
//    public Long getUserId(){ return userEntity.getUserId();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    //...
}
