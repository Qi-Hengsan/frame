package com.wwj.authority.util;

import cn.hutool.core.util.ObjectUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserInfoUtils {

    private static ThreadLocal<Map<String,Object>> userInfo = new ThreadLocal<>();

    public static void put(String key, Object value){
        Map<String, Object> userInfoTemp = userInfo.get();
        if(ObjectUtil.isNull(userInfoTemp)){
            userInfoTemp = new HashMap<>();
            userInfo.set(userInfoTemp);
        }
        userInfoTemp.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> userInfoTemp = userInfo.get();
        return userInfoTemp == null ? null : userInfoTemp.get(key);
    }

    public static void remove() {
        userInfo.remove();
    }

}
