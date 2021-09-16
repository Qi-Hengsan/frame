package com.wwj.common.filter;

import cn.hutool.core.util.StrUtil;
import com.wwj.authority.util.JwtTokenUtil;
import com.wwj.authority.util.UserInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * OncePerRequestFilter: 能够保证过滤只执行一次
 * @author 阿涛
 * @date 2021-06-01
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("agentDetailsService")
    private UserDetailsService agentDetailsService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 拿到JWT令牌
        String jwt = request.getHeader(tokenHeader);
        // 判断头中是否加了tokenHead Bearer
        if (!StrUtil.isBlank(jwt) && jwt.startsWith(tokenHead)) {
            // 解密
            jwt = jwt.substring(tokenHead.length());
            String userName = jwtTokenUtil.getUserNameFromToken(jwt);
            // 把用户id放入线程缓存中，程序中使用
            UserInfoUtils.put("userId", jwtTokenUtil.getUserIdFromToken(jwt));
            // 把用户名放入线程缓存中，程序适用
            UserInfoUtils.put("userName", userName);
            if (!StrUtil.isBlank(userName)) {
                // 从服务器中查询
                UserDetails userDetails = null;
                if (Objects.nonNull(userDetailsService.loadUserByUsername(userName))) {
                    userDetails = userDetailsService.loadUserByUsername(userName);
                } else {
                    userDetails = agentDetailsService.loadUserByUsername(userName);
                }
                if (userDetails != null) {
                    // 生成spring security通过的认证标识
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails
                            , null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }





}
