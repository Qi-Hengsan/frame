package com.wwj.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wwj.authority.util.UserInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Classname MyMetaObjectHandler
 * @Description mybatis-plus自动填充
 * @Date 2021/8/11 16:52
 * @author by ztsong
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createUser", UserInfoUtils.get("userName"), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateUser", UserInfoUtils.get("userName"), metaObject);
    }
}
