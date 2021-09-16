package com.wwj.common.module.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wwj.core.service.impl.BaseServiceImpl;
import com.wwj.common.module.sys.VO.SysMenuByLoginTreeVO;
import com.wwj.common.module.sys.VO.SysMenuTreeVO;
import com.wwj.common.module.sys.VO.query.MenuRoleVO;
import com.wwj.common.module.sys.VO.query.SysMenuQueryVO;
import com.wwj.common.module.sys.entity.SysMenu;
import com.wwj.common.module.sys.entity.SysRoleMenuRelation;
import com.wwj.common.module.sys.mapper.SysMenuMapper;
import com.wwj.common.module.sys.mapper.SysRoleMapper;
import com.wwj.common.module.sys.mapper.SysRoleMenuRelationMapper;
import com.wwj.common.module.sys.service.SysMenuService;
import com.wwj.common.utils.PojoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 后台菜单表 服务实现类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Slf4j
@Service
public class SysMenuServiceImpl
    extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuRelationMapper sysRoleMenuRelationMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysMenuTreeVO> getMenu() {
        List<SysMenuQueryVO> list = sysMenuMapper.getAllMenu();
        List<SysMenuTreeVO> result = generateMenuInfoTree(list);
        return result;
    }

    @Override
    public List<SysMenuByLoginTreeVO> getMenuByLogin(Integer userId) {
        // 判断该角色是否被禁用
        int flag = sysRoleMapper.getStatusByUserId(userId);
        if (Objects.equals(flag, 0)) {
            return new ArrayList<SysMenuByLoginTreeVO>();
        }
        List<Integer> allEndMenuId = sysMenuMapper.getAllEndMenuIdForRole(userId);
        List<SysMenu> allMenuVOS = new ArrayList<>();
        for (Integer menuId : allEndMenuId) {
            List<SysMenu> tempVOS = sysMenuMapper.getAllMenuIdForRole(menuId);
            allMenuVOS.addAll(tempVOS);
        }
        List<SysMenuByLoginTreeVO> result = generateMenuByLoginTree(allMenuVOS, userId);
        return result;
    }

    @Override
    public List<MenuRoleVO> getAllResourceRole() {
        return baseMapper.getAllResourceRole();
    }

    @Override
    public List<SysMenuTreeVO> getMenuByRole(Integer roleId) {
        List<SysMenuQueryVO> list = sysMenuMapper.getAllMenu();
        List<SysMenuTreeVO> result = generateMenuInfoByRoleTree(list, roleId);
        return result;
    }

    @Override
    public List<Integer> getAlreadyMenu(Integer roleId) {
        List<Integer> result = sysRoleMenuRelationMapper.selectList(new LambdaQueryWrapper<SysRoleMenuRelation>()
                .eq(SysRoleMenuRelation::getRoleId, roleId))
                .stream()
                .map(SysRoleMenuRelation::getMenuId)
                .collect(Collectors.toList());
        return result;
    }

    private List<SysMenuTreeVO> generateMenuInfoByRoleTree(List<SysMenuQueryVO> list, int roleId) {
        List<SysMenuTreeVO> menuTreeVOS = PojoUtils.convert(list, SysMenuTreeVO.class);
        List<SysMenuTreeVO> result = menuTreeVOS.stream().filter(menu -> Objects.equals(menu.getParentId(), 0L))
                .map(menu -> {
                    menu.setChildMenu(getChildrensByRole(menu, menuTreeVOS, roleId));
                    return menu;
                }).collect(Collectors.toList());
        return result;
    }

    private List<SysMenuTreeVO> getChildrensByRole(SysMenuTreeVO menu, List<SysMenuTreeVO> menuTreeVOS, int roleId) {
        List<SysMenuTreeVO> childrens = menuTreeVOS.stream().filter(one -> Objects.equals(one.getParentId(), menu.getId()))
                .map(one -> {
                    if (CollectionUtil.isEmpty(getChildrensByRole(one, menuTreeVOS, roleId))) {
                        SysRoleMenuRelation relation = sysRoleMenuRelationMapper.selectOne(new LambdaQueryWrapper<SysRoleMenuRelation>()
                                .eq(SysRoleMenuRelation::getMenuId, one.getId())
                                .eq(SysRoleMenuRelation::getRoleId, roleId));
                        if (Objects.nonNull(relation)) {
                            if (Objects.equals(relation.getPermission(), "edit")) {
                                one.setEdit(1);
                            } else {
                                one.setEdit(0);
                            }
                        }
                        one.setShowPermission(true);
                    }
                    one.setChildMenu(getChildrens(one, menuTreeVOS));
                    return one;
                }).collect(Collectors.toList());
        return childrens;
    }



    private List<SysMenuByLoginTreeVO> generateMenuByLoginTree(List<SysMenu> list, int userId) {
        List<SysMenuByLoginTreeVO> result = new ArrayList<>();
        list.stream().distinct().sorted(Comparator.comparing(SysMenu::getLevel).thenComparing(SysMenu::getSort))
                .filter(menu -> Objects.equals(menu.getParentId(), 0L))
                .forEach(menu -> {
                    SysMenuByLoginTreeVO treeVO = PojoUtils.convertOne(menu, SysMenuByLoginTreeVO.class);
                    treeVO.setTabIndex(menu.getLevel());
                    if (CollectionUtil.isEmpty(getMenuByLoginChildren(menu, list, userId))) {
                        treeVO.setChildren(null);
                    } else {
                        treeVO.setChildren(getMenuByLoginChildren(menu, list, userId));
                    }
                    result.add(treeVO);
                });
        return result;
    }

    private List<SysMenuByLoginTreeVO> getMenuByLoginChildren(SysMenu menu, List<SysMenu> menuTreeVOS, int userId) {
        List<SysMenuByLoginTreeVO> result = new ArrayList<>();
        menuTreeVOS.stream().distinct().sorted(Comparator.comparing(SysMenu::getLevel).thenComparing(SysMenu::getSort))
                .filter(one -> Objects.equals(one.getParentId(), menu.getId()))
                .forEach(one -> {
                    SysMenuByLoginTreeVO treeVO = PojoUtils.convertOne(one, SysMenuByLoginTreeVO.class);
                    treeVO.setTabIndex(one.getLevel());
                    if (CollectionUtil.isEmpty(getMenuByLoginChildren(one, menuTreeVOS, userId))) {
                        // 查询userId的所有角色
                        List<String> permissions = sysMenuMapper.getPermission(userId, one.getId());
                        if (CollectionUtil.isNotEmpty(permissions)) {
                            treeVO.setEdit(1);
                        }  else  {
                            treeVO.setEdit(0);
                        }
                        treeVO.setChildren(null);
                    } else {
                        treeVO.setChildren(getMenuByLoginChildren(one, menuTreeVOS, userId));
                    }
                    result.add(treeVO);
                });
        return result;
    }

    private List<SysMenuTreeVO> generateMenuInfoTree(List<SysMenuQueryVO> list) {
        List<SysMenuTreeVO> menuTreeVOS = PojoUtils.convert(list, SysMenuTreeVO.class);
        List<SysMenuTreeVO> result = menuTreeVOS.stream().filter(menu -> Objects.equals(menu.getParentId(), 0L))
                .map(menu -> {
                    menu.setChildMenu(getChildrens(menu, menuTreeVOS));
                    return menu;
                }).collect(Collectors.toList());
        return result;
    }

    private List<SysMenuTreeVO> getChildrens(SysMenuTreeVO menu, List<SysMenuTreeVO> menuTreeVOS) {
        List<SysMenuTreeVO> childrens = menuTreeVOS.stream().filter(one -> Objects.equals(one.getParentId(), menu.getId()))
                .map(one -> {
                    one.setChildMenu(getChildrens(one, menuTreeVOS));
                    return one;
                }).collect(Collectors.toList());
        return childrens;
    }
}
