package com.wwj.authority.componet;

import java.util.List;
import java.util.Map;

/**
 * @author 阿涛
 * @date 2021-06-01
 */
public interface SecurityResourceRoleSource {

    /**
     * 获取所有资源对应的角色
     * key:资源  /admin/***
     * value:角色
     * @return
     */
    Map<String, List<String>> getResourceRole();

}
