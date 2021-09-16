package com.wwj.common.module.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.wwj.common.aspect.LoginLog;
import com.wwj.common.module.sys.VO.LoginUserInfoVO;
import com.wwj.common.module.sys.param.LoginParam;
import com.wwj.common.module.teacher.service.TeacherService;
import com.wwj.core.api.ApiCode;
import com.wwj.core.api.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author by ztsong
 * @Classname LoginController
 * @Description TODO
 * @Date 2021/8/19 16:58
 */
@Slf4j
@RestController
@RequestMapping("/teacher")
@Api(value = "客户端API", tags = {"教师登录"})
public class LoginController {

    @Resource
    private TeacherService teacherService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    @LoginLog()
    public ApiResult<?> login(@Validated @RequestBody LoginParam loginParam) {
        if (StrUtil.isBlank(loginParam.getUsername())) {
            return ApiResult.result(ApiCode.NOT_USERNAME_EXCEPTION);
        }
        if (StrUtil.isBlank(loginParam.getPassword())) {
            return ApiResult.result(ApiCode.NOT_PASSWORD_EXCEPTION);
        }
        LoginUserInfoVO result = teacherService.login(loginParam.getUsername(), loginParam.getPassword());
        if (Objects.equals(result.getToken(), "该账号被禁用")) {
            return ApiResult.result(ApiCode.NOT_SUPPORTED_USERLOGININFO_EXCEPTION);
        }
        if (StrUtil.isBlank(result.getToken())) {
            return ApiResult.result(ApiCode.ERROR_USERLOGININFO_EXCEPTION);
        }
        return ApiResult.result(ApiCode.LOGIN_SUCCESS, result);
    }

}
