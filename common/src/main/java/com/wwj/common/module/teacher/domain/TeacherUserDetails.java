package com.wwj.common.module.teacher.domain;

import com.wwj.common.module.teacher.entity.Teacher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author 12249195@qq.com
 * @Classname TeacherUserDetails
 * @Description TODO
 * @Date 2021/8/19 17:15
 */
public class TeacherUserDetails implements UserDetails {

    /**
     * 教师信息
     */
    Teacher teacher;

    public TeacherUserDetails(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return teacher.getPassword();
    }

    @Override
    public String getUsername() {
        return teacher.getUsername();
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
        return teacher.getStatus() == 1;
    }
}
