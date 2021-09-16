package com.wwj.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.wwj.authority.componet.DynamicAccessDecisionManager;
import com.wwj.authority.componet.DynamicSecurityMetadataSource;
import com.wwj.authority.componet.RestfulAccessDeniedHandler;
import com.wwj.authority.componet.RestfulAuthenticationEntryPoint;
import com.wwj.authority.config.IgnoredUrlsConfig;
import com.wwj.authority.filter.DynamicSecurityFilter;
import com.wwj.authority.util.JwtTokenUtil;
import com.wwj.common.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 阿涛
 * @date 2021-06-01
 */
@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
//     * 由于前台服务没有动态权限功能，所以要配置required = false
//     */
//    @Autowired(required = false)
//    SecurityResourceRoleSource securityResourceRoleSource;

    @Autowired(required = false)
    private DynamicSecurityMetadataSource dynamicSecurityService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        // 白名单放行
        for (String url : ignoredUrlsConfig().getUrls()) {
            registry.antMatchers(url).permitAll();
        }

//        // 静态资源权限
//        if (securityResourceRoleSource != null) {
//            Map<String, List<String>> resourceRole = securityResourceRoleSource.getResourceRole();
//            //循环注册registry.antMatchers("/product").hasAnyAuthority("xxx管理员")
//            for (String resource : resourceRole.keySet()) {
//                // 将List转换数组， 将object[] 转换string[]
//                List<String> roles = resourceRole.get(resource);
//                registry.antMatchers(resource).hasAnyAuthority(roles.toArray(new String[roles.size()]));
//            }
//        }


        // 允许可以请求options
        registry.antMatchers(HttpMethod.OPTIONS).permitAll()
                    .anyRequest().authenticated() // 任何请求都需要认证
                .and()
                    .cors() // 支持跨域
                .and()
                    .csrf().disable() // 关闭csrf跨站请求伪造：因为使用JWT来实现认证
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // 禁止session
                .and()
                    .exceptionHandling() // 自定义拒绝处理类
                    .accessDeniedHandler(restfulAccessDeniedHandler()) // 没有权限访问时的处理类
                    .authenticationEntryPoint(restfulAuthenticationEntryPoint()) // 没有登录时的处理类
                .and()
                    .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 加入JWT认证过滤器


        // 有动态权限配置时添加动态权限校验过滤器
        if (ObjectUtil.isNotNull(dynamicSecurityService)) {
            registry.and().addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
        }

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IgnoredUrlsConfig ignoredUrlsConfig() {
        return new IgnoredUrlsConfig();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint() {
        return new RestfulAuthenticationEntryPoint();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }

    /**
     * 作用：根据当前请求url获取对应角色
     * @return
     */
    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }

    /**
     * 作用：在FilterSecurityInterceptor前面的自定义过滤器
     * @return
     */
    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityFilter dynamicSecurityFilter() {
        return new DynamicSecurityFilter();
    }

    /**
     * 作用：鉴权
     * @return
     */
    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
        return new DynamicSecurityMetadataSource();
    }

}
