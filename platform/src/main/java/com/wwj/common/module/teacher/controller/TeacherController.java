package com.wwj.common.module.teacher.controller;


import com.wwj.common.module.teacher.VO.TeacherVO;
import com.wwj.common.module.teacher.param.TeacherAddParam;
import com.wwj.common.module.teacher.param.TeacherPageParam;
import com.wwj.common.module.teacher.param.TeacherUpdateParam;
import com.wwj.common.module.teacher.service.TeacherService;
import com.wwj.core.api.ApiResult;
import com.wwj.core.pagination.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wushilin
 * @version 1.0
 * @date 2021/08/18 17:31:34
 */
@RestController
@RequestMapping("/teacher")
@Api(value = "代理商信息管理Api",tags = {"代理商信息管理"})
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @PostMapping("/listPage")
    @ApiOperation(value = "代理商分页")
    public ApiResult<Paging<TeacherVO>> listPage(@RequestBody TeacherPageParam query ){
        return ApiResult.ok(teacherService.listPage(query));
    }

    @PutMapping("/resetPassword")
    @ApiOperation(value = "重置密码")
    public ApiResult<?> resetPassword(@RequestBody TeacherVO vo){
        teacherService.resetPassword(vo.getId());
        return ApiResult.ok(vo.getId());
    }

    @PostMapping("/updateStatus")
    @ApiOperation(value = "修改状态")
    public ApiResult<?> updateStatus(@RequestBody TeacherUpdateParam param ){
        teacherService.batchUpdateStatus(param);
        return ApiResult.ok("");
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public ApiResult<?> add(@RequestBody TeacherAddParam param ){
        teacherService.add(param);
        return ApiResult.ok("");
    }
}
