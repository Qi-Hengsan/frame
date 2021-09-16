package com.wwj.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统常量
 */
public class SysConstant {

    //导出 公开信息发布
    public static final Map<Integer,String> ISUSED_MAP = new HashMap<>();
    static{
        ISUSED_MAP.put(1,"启用");
        ISUSED_MAP.put(0,"禁用");
    }

    //导出 用户信息
    public static final Map<Integer,String> SEX_MAP = new HashMap<>();
    static{
        SEX_MAP.put(1,"男");
        SEX_MAP.put(0,"女");
    }

}
