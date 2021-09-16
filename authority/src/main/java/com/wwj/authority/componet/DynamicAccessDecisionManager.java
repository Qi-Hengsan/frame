package com.wwj.authority.componet;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * 动态权限决策管理器，用于判断用户是否有访问权限
 * @author 阿涛
 * @date 2021-06-02
 */
public class DynamicAccessDecisionManager implements AccessDecisionManager {

    /**
     *
     * @param authentication 当前用户
     * @param o
     * @param configAttributes 角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 当接口未被配置资源时，直接放行
        if (CollUtil.isEmpty(configAttributes)) return;
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        // 循环上一步的角色
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            //将访问所需资源或用户拥有资源进行比对
            String needAuthority = configAttribute.getAttribute();
            // 根据当前用户角色 循环
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {

                if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("抱歉，您没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
