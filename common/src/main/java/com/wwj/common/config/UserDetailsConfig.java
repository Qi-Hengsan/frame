package com.wwj.common.config;

import com.wwj.authority.componet.DynamicSecurityService;
import com.wwj.authority.componet.SecurityResourceRoleSource;
import com.wwj.common.module.teacher.service.TeacherService;
import com.wwj.common.module.sys.VO.query.MenuRoleVO;
import com.wwj.common.module.sys.service.SysAdminService;
import com.wwj.common.module.sys.service.SysMenuService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Classname UserDetailsConfig
 * @Description TODO
 * @Date 2021/8/11 17:10
 * @author by ztsong
 */
@Configuration
@EnableWebSecurity  // 启动
@Order(2)
public class UserDetailsConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private SysAdminService sysAdminService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private SysMenuService sysMenuService;

    /**
     * 认证交给spring security
     * @return
     */
    @Override
    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        return userName -> sysAdminService.loadUserByUsername(userName);
    }

    @Bean
    public UserDetailsService agentDetailsService() {
        return userName -> teacherService.loadUserByUsername(userName);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider userAuthenticationProvider = new DaoAuthenticationProvider();
        DaoAuthenticationProvider agentAuthenticationProvider = new DaoAuthenticationProvider();
        userAuthenticationProvider.setUserDetailsService(userDetailsService());
        agentAuthenticationProvider.setUserDetailsService(agentDetailsService());
        ProviderManager providerManager = new ProviderManager(userAuthenticationProvider, agentAuthenticationProvider);
        return providerManager;
    }

    @Bean
    public SecurityResourceRoleSource securityResourceRoleSource() {
        return () -> {
            // 调用业务逻辑类查询资源对应的角色信息
            List<MenuRoleVO> list = sysMenuService.getAllResourceRole();
            Map<String, List<String>> map=new HashMap<>();
            for (MenuRoleVO menuRoleVO : list) {
                List<String> roleNameList = menuRoleVO.getRoleList()
                        .stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toList());
                map.put(menuRoleVO.getPath(), roleNameList);
            }
            return map;
        };
    }

    // 获取最新的资源角色信息
    @Bean("dynamicSecurityService")
    public DynamicSecurityService dynamicSecurityService(SysMenuService sysMenuService) {
        return () -> {
            Map<RequestMatcher, List<ConfigAttribute>> map = new ConcurrentHashMap<>();
            List<MenuRoleVO> list = sysMenuService.getAllResourceRole();
            for (MenuRoleVO menuRoleVO : list) {
                // 通配符匹配器
                map.put(new AntPathRequestMatcher(menuRoleVO.getPath()),
                        // 所有角色信息
                        menuRoleVO.getRoleList().stream()
                                .map(role-> new org.springframework.security.access.SecurityConfig(role.getName()))
                                .collect(Collectors.toList())
                );
            }
            return map;
        };
    }


}
