package com.wwj.authority.componet;

import cn.hutool.json.JSONUtil;
import com.wwj.core.api.ApiCode;
import com.wwj.core.api.ApiResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有权限时的访问处理类
 * @author 阿涛
 * @date 2021-06-01
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ApiResult.fail(ApiCode.NOT_PERMISSION)));
        response.getWriter().flush();
    }
}
