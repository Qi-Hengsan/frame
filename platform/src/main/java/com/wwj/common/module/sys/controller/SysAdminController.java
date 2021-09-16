package com.wwj.common.module.sys.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.wwj.core.api.ApiCode;
import com.wwj.core.api.ApiResult;
import com.wwj.core.controller.BaseController;
import com.wwj.core.pagination.Paging;
import com.wwj.common.aspect.LoginLog;
import com.wwj.common.aspect.OprationLog;
import com.wwj.common.constant.SysConstant;
import com.wwj.common.enums.SaveEnum;
import com.wwj.common.module.sys.VO.*;
import com.wwj.common.module.sys.VO.query.SysAdminQueryVO;
import com.wwj.common.module.sys.param.*;
import com.wwj.common.module.sys.service.SysAdminService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.Sheet;
import com.wwj.common.module.sys.VO.SysAdminInfoVO;
import com.wwj.common.module.sys.VO.SysUpdateAdminInfoVO;
import com.wwj.common.module.sys.param.SysAdminBaseParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * 后台用户表 控制器
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Slf4j
@RestController
@RequestMapping("/sysAdmin")
@Api(value = "后台用户表API", tags = {"后台用户表"})
public class SysAdminController extends BaseController {

    @Resource
    private SysAdminService sysAdminService;

    /**
     * 添加后台用户表
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加后台用户表", response = ApiResult.class)
    @OprationLog(operModul = "用户管理", operDesc = "新增用户")
    public ApiResult<?> addSysAdmin(@RequestBody SysAdminBaseParam param) throws Exception {
        int row = sysAdminService.saveSysAdmin(param);
        if(ObjectUtil.equals(SaveEnum.SAVE_SUCCESS.getId(),row)){
            return ApiResult.result(ApiCode.SUCCESS,SaveEnum.getNameById(row));
        }
        return ApiResult.fail(ApiCode.FAIL,SaveEnum.getNameById(row));
    }

    /**
     * 修改后台用户表
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改后台用户表", response = ApiResult.class)
    @OprationLog(operModul = "用户管理", operDesc = "编辑用户")
    public ApiResult<Boolean> updateSysAdmin(@RequestBody SysAdminBaseParam param) throws Exception {
        boolean flag = sysAdminService.updateSysAdmin(param);
        return ApiResult.result(flag);
    }

    /**
     * 删除后台用户表
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除后台用户表", response = ApiResult.class)
    @OprationLog(operModul = "用户管理", operDesc = "删除用户")
    public ApiResult<Boolean> deleteSysAdmin(@PathVariable("id") Integer id) throws Exception {
        boolean flag = sysAdminService.deleteSysAdmin(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取后台用户表详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "后台用户表详情", response = SysAdminQueryVO.class)
    public ApiResult<SysUpdateAdminInfoVO> getSysAdmin(@PathVariable("id") Integer id) {
        SysUpdateAdminInfoVO sysAdminQueryVO = sysAdminService.getUpdateAdminInfo(id);
        return ApiResult.ok(sysAdminQueryVO);
    }

    /**
     * 后台用户表分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "后台用户表分页列表", response = SysAdminInfoVO.class)
    public ApiResult<Paging<SysAdminInfoVO>> getSysAdminPageList(@RequestBody SysAdminPageParam sysAdminPageParam) throws Exception {
        Paging<SysAdminInfoVO> paging = sysAdminService.getSysAdminPageList(sysAdminPageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 导出用户管理信息
     */
    @PostMapping("/exportSysAdmin")
    @ApiOperation(value = "导出用户管理信息", response = SysAdminInfoVO.class)
    @ResponseExcel(name = "用户管理",sheets = {@Sheet(sheetName = "用户管理信息")})
    @OprationLog(operModul = "用户管理", operDesc = "导出用户信息")
    public List<SysAdminInfoVO> exportSysAdmin(@RequestBody SysAdminPageParam sysAdminPageParam) throws Exception {
        Paging<SysAdminInfoVO> paging = sysAdminService.getSysAdminPageList(sysAdminPageParam);
        paging.getRecords().forEach(s ->{
            s.setSexName(SysConstant.SEX_MAP.get(s.getSex()));
            s.setStatusName(SysConstant.ISUSED_MAP.get(s.getStatus()));

        });
        return paging.getRecords();
    }

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
        LoginUserInfoVO result = sysAdminService.login(loginParam.getUsername(), loginParam.getPassword());
        if (Objects.equals(result.getToken(), "该账号被禁用")) {
            return ApiResult.result(ApiCode.NOT_SUPPORTED_USERLOGININFO_EXCEPTION);
        }
        if (StrUtil.isBlank(result.getToken())) {
            return ApiResult.result(ApiCode.ERROR_USERLOGININFO_EXCEPTION);
        }
        return ApiResult.result(ApiCode.LOGIN_SUCCESS, result);
    }

    @GetMapping("/listOrgInfo")
    @ApiOperation(value = "所属部门下拉")
    public ApiResult<List<SysAdminListOrgInfoVO>> listOrgInfo() {
        List<SysAdminListOrgInfoVO> result = sysAdminService.listOrgInfo();
        return ApiResult.ok(result);
    }

    @GetMapping("/listRoleInfo")
    @ApiOperation(value = "所属角色下拉")
    public ApiResult<List<SysAdminRoleInfoVO>> listRoleInfo() {
        List<SysAdminRoleInfoVO> result = sysAdminService.listRoleInfo();
        return ApiResult.ok(result);
    }

    @PostMapping("/updateStatus")
    @ApiOperation(value = "启用状态修改")
    @OprationLog(operModul = "用户管理", operDesc = "启用状态修改")
    public ApiResult<?> updateStatus(@RequestBody UpdateStatusParam param) {
        boolean flag = sysAdminService.updateStatus(param);
        return ApiResult.result(flag);
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码")
    @OprationLog(operModul = "系统设置", operDesc = "修改密码")
    public ApiResult<?> updatePassword(@RequestBody SysAdminUpdatePasswdParam passwdParam) {
        boolean flag = sysAdminService.updatePassword(passwdParam);
        return ApiResult.result(flag);
    }

    @ApiOperation(value = "刷新token")
    @GetMapping(value = "/refreshToken")
    @ResponseBody
    public ApiResult<?> refreshToken(HttpServletRequest request) {
        LoginUserInfoVO result = sysAdminService.refreshToken(request);
        if (result == null) {
            return ApiResult.fail("token已经过期");
        }
        return ApiResult.ok(result);
    }

}

