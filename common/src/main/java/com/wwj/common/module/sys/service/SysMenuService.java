package com.wwj.common.module.sys.service;


import com.wwj.core.service.BaseService;
import com.wwj.common.module.sys.VO.SysMenuByLoginTreeVO;
import com.wwj.common.module.sys.VO.SysMenuTreeVO;
import com.wwj.common.module.sys.VO.query.MenuRoleVO;
import com.wwj.common.module.sys.entity.SysMenu;

import java.util.List;

/**
 * 后台菜单表 服务类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 设置权限时获取所有菜单
     * @return
     */
    List<SysMenuTreeVO> getMenu();

    /**
     * 登录时根据用户查询角色获取菜单
     * @param userId
     * @return
     */
    List<SysMenuByLoginTreeVO> getMenuByLogin(Integer userId);

    /**
     * 查询资源对应的角色信息
     * @return
     */
    List<MenuRoleVO> getAllResourceRole();

    /**
     * 根据用户角色获取菜单权限
     * @param roleId
     * @return
     */
    List<SysMenuTreeVO> getMenuByRole(Integer roleId);

    /**
     * 根据角色ID查询已选择的菜单
     * @param roleId
     * @return
     */
    List<Integer> getAlreadyMenu(Integer roleId);
}
