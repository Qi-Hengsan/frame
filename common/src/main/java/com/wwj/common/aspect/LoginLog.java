package com.wwj.common.aspect;

import java.lang.annotation.*;

/**
 * @Classname LoginLog
 * @Description 登录日志注解
 * @Date 2021/7/7 15:05
 * @author by ztsong
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginLog {
}
