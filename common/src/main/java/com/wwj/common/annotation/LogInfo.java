package com.wwj.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName LogInfo
 * @Description
 * @Author 谭雨
 * @Date 2021/5/25 15:00
 * @Version: 1.0
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogInfo {
    String value() default "";
}
