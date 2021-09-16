package com.wwj.common.module.sys.controller;

import com.wwj.core.api.ApiResult;
import com.wwj.core.controller.BaseController;
import com.wwj.common.module.sys.VO.SysMenuByLoginTreeVO;
import com.wwj.common.module.sys.VO.SysMenuTreeVO;
import com.wwj.common.module.sys.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 后台菜单表 控制器
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Slf4j
@RestController
@RequestMapping("/sysMenu")

@Api(value = "后台菜单表API", tags = {"后台菜单表"})
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

//    @GetMapping("/getMenu")
//    @ApiOperation(value = "设置权限时获取所有菜单")
//    public ApiResult<List<SysMenuTreeVO>> getMenu() {
//        List<SysMenuTreeVO> result = sysMenuService.getMenu();
//        return ApiResult.ok(result);
//    }

    @GetMapping("/getMenuByRole/{roleId}")
    @ApiOperation(value = "根据角色ID获取所有菜单")
    public ApiResult<List<SysMenuTreeVO>> getMenuByRole(@PathVariable("roleId") int roleId) {
        List<SysMenuTreeVO> result = sysMenuService.getMenuByRole(roleId);
        return ApiResult.ok(result);
    }

    @GetMapping("/getMenuByLogin/{userId}")
    @ApiOperation(value = "登录时获取所有菜单")
    public ApiResult<List<SysMenuByLoginTreeVO>> getMenuByLogin(@PathVariable("userId") int userId) {
        List<SysMenuByLoginTreeVO> result = sysMenuService.getMenuByLogin(userId);
        return ApiResult.ok(result);
    }

    @GetMapping("/getAlreadyMenu/{roleId}")
    @ApiOperation(value = "根据角色ID查询已选择的菜单")
    public ApiResult<List<Integer>> getAlreadyMenu(@PathVariable("roleId") int roleId) {
        List<Integer> result = sysMenuService.getAlreadyMenu(roleId);
        return ApiResult.ok(result);
    }

}

