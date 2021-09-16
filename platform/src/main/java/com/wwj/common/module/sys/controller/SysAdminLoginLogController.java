package com.wwj.common.module.sys.controller;

import com.wwj.core.api.ApiResult;
import com.wwj.core.controller.BaseController;
import com.wwj.core.pagination.Paging;
import com.wwj.common.aspect.OprationLog;
import com.wwj.common.module.sys.VO.LoginLogVO;
import com.wwj.common.module.sys.param.SysAdminLoginLogPageParam;
import com.wwj.common.module.sys.service.SysAdminLoginLogService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.Sheet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台用户登录日志表 控制器
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Slf4j
@RestController
@RequestMapping("/sysAdminLoginLog")

@Api(value = "后台用户登录日志表API", tags = {"后台用户登录日志表"})
public class SysAdminLoginLogController extends BaseController {

    @Resource
    private SysAdminLoginLogService sysAdminLoginLogService;

    /**
     * 后台用户登录日志表分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "后台用户登录日志表分页列表", response = LoginLogVO.class)
    public ApiResult<Paging<LoginLogVO>> getSysAdminLoginLogPageList(@RequestBody SysAdminLoginLogPageParam sysAdminLoginLogPageParam) throws Exception {
        Paging<LoginLogVO> paging = sysAdminLoginLogService.getSysAdminLoginLogPageList(sysAdminLoginLogPageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 导出用户登录日志
     */
    @PostMapping("/exportPageList")
    @ApiOperation(value = "导出用户登录日志", response = LoginLogVO.class)
    @ResponseExcel(name ="用户登录信息",sheets = {@Sheet(sheetName = "用户登录信息")})
    @OprationLog(operModul = "用户登录",operDesc = "导出用户登录信息")
    public List<LoginLogVO> exportPageList(@RequestBody SysAdminLoginLogPageParam sysAdminLoginLogPageParam) throws Exception {
        Paging<LoginLogVO> paging = sysAdminLoginLogService.getSysAdminLoginLogPageList(sysAdminLoginLogPageParam);
        return paging.getRecords();
    }

}

