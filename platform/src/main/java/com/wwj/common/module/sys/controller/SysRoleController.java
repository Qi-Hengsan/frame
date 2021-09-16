package com.wwj.common.module.sys.controller;

import com.wwj.core.api.ApiCode;
import com.wwj.core.api.ApiResult;
import com.wwj.core.controller.BaseController;
import com.wwj.core.exception.GlobalException;
import com.wwj.core.pagination.Paging;
import com.wwj.common.aspect.OprationLog;
import com.wwj.common.constant.SysConstant;
import com.wwj.common.module.sys.VO.SysAdminListOrgUserVO;
import com.wwj.common.module.sys.VO.query.SysRolePageVO;
import com.wwj.common.module.sys.VO.query.SysRoleQueryVO;
import com.wwj.common.module.sys.param.*;
import com.wwj.common.module.sys.service.SysRoleService;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.Sheet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台用户角色表 控制器
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Slf4j
@RestController
@RequestMapping("/sysRole")

@Api(value = "后台用户角色表API", tags = {"后台用户角色表"})
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 添加后台用户角色表
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加后台用户角色表", response = ApiResult.class)
    @OprationLog(operModul = "角色权限", operDesc = "增加角色")
    public ApiResult<?> addSysRole(@RequestBody SysRoleAddParam param) {
        try {
            boolean flag = sysRoleService.saveSysRole(param);
            return ApiResult.result(flag);
        } catch (GlobalException e) {
            return ApiResult.result(ApiCode.getApiCode(e.getErrorCode()));
        }
    }

    /**
     * 修改后台用户角色表
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改后台用户角色表", response = ApiResult.class)
    @OprationLog(operModul = "角色权限", operDesc = "修改角色")
    public ApiResult<?> updateSysRole(@RequestBody SysRoleUpdateParam param){
        try {
            boolean flag = sysRoleService.updateSysRole(param);
            return ApiResult.result(flag);
        } catch (GlobalException e) {
            return ApiResult.result(ApiCode.getApiCode(e.getErrorCode()));
        }

    }

    /**
     * 删除后台用户角色表
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除后台用户角色表", response = ApiResult.class)
    @OprationLog(operModul = "角色权限", operDesc = "删除角色")
    public ApiResult<Boolean> deleteSysRole(@PathVariable("id") Integer id) throws Exception {
        boolean flag = sysRoleService.deleteSysRole(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取后台用户角色表详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "后台用户角色表详情", response = SysRoleQueryVO.class)
    public ApiResult<SysRoleQueryVO> getSysRole(@PathVariable("id") Long id) throws Exception {
        SysRoleQueryVO sysRoleQueryVO = sysRoleService.getSysRoleById(id);
        return ApiResult.ok(sysRoleQueryVO);
    }

    /**
     * 后台用户角色表分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "后台用户角色表分页列表", response = SysRoleQueryVO.class)
    public ApiResult<Paging<SysRolePageVO>> getSysRolePageList(@RequestBody SysRolePageParam sysRolePageParam) throws Exception {
        Paging<SysRolePageVO> paging = sysRoleService.getSysRolePageList(sysRolePageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 导出用户角色表
     */
    @PostMapping("/exportPageList")
    @ApiOperation(value = "导出用户角色表", response = SysRoleQueryVO.class)
    @ResponseExcel(name = "角色权限",sheets = {@Sheet(sheetName = "角色权限信息")})
    @OprationLog(operModul = "角色权限", operDesc = "导出用户角色表信息")
    public List<SysRolePageVO> exportPageList(@RequestBody SysRolePageParam sysRolePageParam) throws Exception {
        Paging<SysRolePageVO> paging = sysRoleService.getSysRolePageList(sysRolePageParam);
        paging.getRecords().forEach(s -> s.setStatusName(SysConstant.ISUSED_MAP.get(s.getStatus())));
        return paging.getRecords();
    }


    @PostMapping("/updateStatus")
    @ApiOperation(value = "启用状态修改")
    @OprationLog(operModul = "角色权限", operDesc = "修改角色启用状态")
    public ApiResult<?> updateStatus(@RequestBody UpdateStatusParam param) {
        boolean flag = sysRoleService.updateStatus(param);
        return ApiResult.result(flag);
    }

    @GetMapping("/getOrgUserList")
    @ApiOperation(value = "角色权限-添加人员-全部部门员工数据")
    public ApiResult<List<SysAdminListOrgUserVO>> getOrgUserList() {
        List<SysAdminListOrgUserVO> result = sysRoleService.getOrgUserList();
        return ApiResult.ok(result);
    }

    @GetMapping("/getAlreadyOrgUser/{roleId}")
    @ApiOperation(value = "角色权限-添加人员-已选择的员工编号")
    public ApiResult<List<Integer>> getAlreadyOrgUser(@PathVariable("roleId") Integer roleId) {
        List<Integer> result = sysRoleService.getAlreadyOrgUser(roleId);
        return ApiResult.ok(result);
    }

    @PostMapping("/saveRoleUser")
    @ApiOperation(value = "角色权限-添加人员-保存")
    @OprationLog(operModul = "角色权限", operDesc = "添加人员")
    public ApiResult<?> saveRoleUser(@RequestBody SysRoleUserParam param) {
        boolean result = sysRoleService.saveRoleUser(param);
        return ApiResult.ok(result);
    }

    @PostMapping("/saveRolePermission")
    @ApiOperation(value = "角色权限-权限设置-保存")
    @OprationLog(operModul = "角色权限", operDesc = "设置权限")
    public ApiResult<?> saveRolePermission(@RequestBody SysRolePermissionParam param) {
        sysRoleService.saveRolePermission(param);
        return ApiResult.ok();
    }

    @GetMapping("/getNoChoiceOrgUser/{roleId}")
    @ApiOperation(value = "角色权限-添加人员-未选择的部门员工")
    public ApiResult<List<SysAdminListOrgUserVO>> getNoChoiceOrgUser(@PathVariable("roleId") Integer roleId) {
        List<SysAdminListOrgUserVO> result = sysRoleService.getNoChoiceOrgUser(roleId);
        return ApiResult.ok(result);
    }

    @GetMapping("/getAlreadyChoiceOrgUser/{roleId}")
    @ApiOperation(value = "角色权限-添加人员-已选择的部门员工")
    public ApiResult<List<SysAdminListOrgUserVO>> getAlreadyChoiceOrgUser(@PathVariable("roleId") Integer roleId) {
        List<SysAdminListOrgUserVO> result = sysRoleService.getAlreadyChoiceOrgUser(roleId);
        return ApiResult.ok(result);
    }

}

