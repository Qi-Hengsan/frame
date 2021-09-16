package com.wwj.common.module.sys.domain;

import com.wwj.common.module.sys.entity.SysAdmin;
import com.wwj.common.module.sys.entity.SysRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname AdminUserDetails
 * @Description TODO
 * @Date 2021/8/11 17:00
 * @author by ztsong
 */
public class AdminUserDetails implements UserDetails {

    /**
     * 用户信息
      */
    SysAdmin sysAdmin;

    List<SysRole> roleList;

    public AdminUserDetails(SysAdmin sysAdmin, List<SysRole> roleList) {
        this.sysAdmin = sysAdmin;
        this.roleList = roleList;
    }

    public SysAdmin getSysAdmin() {
        return sysAdmin;
    }

    /**
     * 返回当前用户的角色信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return sysAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return sysAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return sysAdmin.getStatus() == 1;
    }
}

