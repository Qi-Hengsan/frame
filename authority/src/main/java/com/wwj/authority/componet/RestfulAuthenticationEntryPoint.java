package com.wwj.authority.componet;

import cn.hutool.json.JSONUtil;
import com.wwj.core.api.ApiCode;
import com.wwj.core.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有登录时的访问处理类
 * @author 阿涛
 * @date 2021-06-01
 */
@Slf4j
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 响应401 暂未登录或session已经过期
        response.getWriter().println(JSONUtil.parse(ApiResult.fail(ApiCode.UNAUTHORIZED)));
        response.getWriter().flush();
    }
}
