package com.wwj.common.module.sys.controller;

import com.wwj.core.api.ApiResult;
import com.wwj.core.controller.BaseController;
import com.wwj.core.pagination.Paging;
import com.wwj.common.aspect.OprationLog;
import com.wwj.common.module.sys.VO.OperationLogVO;
import com.wwj.common.module.sys.VO.OprationLogSelectVO;
import com.wwj.common.module.sys.param.SysAdminOperationLogPageParam;
import com.wwj.common.module.sys.service.SysAdminOperationLogService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.Sheet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *  控制器
 *
 * @author ztsong
 * @since 2021-07-07
 */
@Slf4j
@RestController
@RequestMapping("/sysAdminOprationLog")

@Api(value = "后台用户操作日志表API", tags = {"后台用户操作日志表"})
public class SysAdminOperationLogController extends BaseController {

    @Resource
    private SysAdminOperationLogService sysAdminOperationLogService;

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "分页列表", response = OperationLogVO.class)
    public ApiResult<Paging<OperationLogVO>> getSysAdminOperationLogPageList(@RequestBody SysAdminOperationLogPageParam sysAdminOperationLogPageParam) throws Exception {
        Paging<OperationLogVO> paging = sysAdminOperationLogService.getSysAdminOperationLogPageList(sysAdminOperationLogPageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 导出系统日志信息
     */
    @PostMapping("/exportPageList")
    @ApiOperation(value = "导出系统日志信息", response = OperationLogVO.class)
    @ResponseExcel(name ="系统日志信息",sheets = {@Sheet(sheetName = "系统日志信息")})
    @OprationLog(operModul = "系统日志",operDesc = "导出系统日志信息")
    public List<OperationLogVO> exportPageList(@RequestBody SysAdminOperationLogPageParam sysAdminOperationLogPageParam) throws Exception {
        Paging<OperationLogVO> paging = sysAdminOperationLogService.getSysAdminOperationLogPageList(sysAdminOperationLogPageParam);
        return paging.getRecords();
    }

    @GetMapping("/getModel")
    @ApiOperation(value = "所有二级操作模块")
    public ApiResult<List<OprationLogSelectVO>> getModel() {
        List<OprationLogSelectVO> result = sysAdminOperationLogService.getModel();
        return ApiResult.ok(result);
    }

}

