package com.wwj.common.utils;

import cn.hutool.core.util.ObjectUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by ztsong
 * @Classname ImportErrorInfoUtils
 * @Description TODO
 * @Date 2021/8/27 17:03
 */
public class ImportErrorInfoUtils {

    private static ThreadLocal<Map<String,Object>> importInfo = new ThreadLocal<>();

    public static void put(String key, Object value){
        Map<String, Object> userInfoTemp = importInfo.get();
        if(ObjectUtil.isNull(userInfoTemp)){
            userInfoTemp = new HashMap<>();
            importInfo.set(userInfoTemp);
        }
        userInfoTemp.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> userInfoTemp = importInfo.get();
        return userInfoTemp == null ? null : userInfoTemp.get(key);
    }

    public static void remove() {
        importInfo.remove();
    }

}
