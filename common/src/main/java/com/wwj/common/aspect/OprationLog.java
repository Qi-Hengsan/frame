package com.wwj.common.aspect;

import java.lang.annotation.*;

/**
 * @Classname OprationLog
 * @Description 实现操作日期的注解
 * @Date 2021/7/7 14:03
 * @author by ztsong
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OprationLog {
    // 操作模块
    String operModul() default "";

    // 操作说明
    String operDesc() default "";
}
