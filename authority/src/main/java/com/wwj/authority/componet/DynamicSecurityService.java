package com.wwj.authority.componet;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;
import java.util.Map;

/**
 * @author 阿涛
 * @date 2021-06-02
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应MAP
     * key: 匹配器 （在DynamicSecurityMetadataSource起作用）
     * value: 资源所对应的角色
     * @return
     */
    Map<RequestMatcher, List<ConfigAttribute>> loadDataSource();

}
