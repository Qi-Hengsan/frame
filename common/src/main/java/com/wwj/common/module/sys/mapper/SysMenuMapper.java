package com.wwj.common.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.common.module.sys.VO.OprationLogSelectVO;
import com.wwj.common.module.sys.VO.query.MenuRoleVO;
import com.wwj.common.module.sys.VO.query.SysMenuQueryVO;
import com.wwj.common.module.sys.entity.SysMenu;
import com.wwj.common.module.sys.param.SysMenuPageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 后台菜单表 Mapper 接口
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysMenuQueryVO getSysMenuById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysMenuPageParam
     * @return
     */
    IPage<SysMenuQueryVO> getSysMenuPageList(@Param("page") Page page
        , @Param("param") SysMenuPageParam sysMenuPageParam);

    /**
     * 获取所有菜单
     * @return
     */
    List<SysMenuQueryVO> getAllMenu();

    /**
     * 获取二级菜单
     * @return
     */
    List<OprationLogSelectVO> getOprationLogSelect();

    /**
     * 根据用户id获取所有角色对应的所有菜单ID
     * @param userId
     * @return
     */
    List<Integer> getAllEndMenuIdForRole(@Param("userId") Integer userId);

    /**
     * 根据最后一级菜单找到所有父级菜单
     * @param menuId
     * @return
     */
    List<SysMenu> getAllMenuIdForRole(@Param("menuId") Integer menuId);

    /**
     * 查询资源对应的角色信息
     * @return
     */
    List<MenuRoleVO> getAllResourceRole();

    /**
     * 查询用户菜单是否有操作权限
     * @param userId
     * @param id
     * @return
     */
    List<String> getPermission(@Param("userId") Integer userId, @Param("menuId") Integer id);
}
