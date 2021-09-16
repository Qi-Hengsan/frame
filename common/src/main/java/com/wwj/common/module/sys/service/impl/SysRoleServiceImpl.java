package com.wwj.common.module.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.core.api.ApiCode;
import com.wwj.core.exception.GlobalException;
import com.wwj.core.pagination.PageInfo;
import com.wwj.core.pagination.Paging;
import com.wwj.core.service.impl.BaseServiceImpl;
import com.wwj.common.module.sys.VO.SysAdminByOrgVO;
import com.wwj.common.module.sys.VO.SysAdminListOrgUserVO;
import com.wwj.common.module.sys.VO.query.AllOrgInfoQueryVO;
import com.wwj.common.module.sys.VO.query.SysRolePageVO;
import com.wwj.common.module.sys.VO.query.SysRoleQueryVO;
import com.wwj.common.module.sys.entity.SysAdminRoleRelation;
import com.wwj.common.module.sys.entity.SysRole;
import com.wwj.common.module.sys.entity.SysRoleMenuRelation;
import com.wwj.common.module.sys.mapper.OrgInfoMapper;
import com.wwj.common.module.sys.mapper.SysAdminRoleRelationMapper;
import com.wwj.common.module.sys.mapper.SysRoleMapper;
import com.wwj.common.module.sys.mapper.SysRoleMenuRelationMapper;
import com.wwj.common.module.sys.param.*;
import com.wwj.common.module.sys.service.SysAdminRoleRelationService;
import com.wwj.common.module.sys.service.SysRoleService;
import com.wwj.common.utils.PojoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 后台用户角色表 服务实现类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Slf4j
@Service
public class SysRoleServiceImpl
    extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private OrgInfoMapper orgInfoMapper;

    @Autowired
    private SysAdminRoleRelationService adminRoleRelationService;

    @Autowired
    private SysRoleMenuRelationMapper sysRoleMenuRelationMapper;

    @Autowired
    private SysAdminRoleRelationMapper sysAdminRoleRelationMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysRole(SysRoleAddParam param) throws GlobalException {
        SysRole existRole = sysRoleMapper.selectOne(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getName, param.getRoleName()));
        if (Objects.nonNull(existRole)) {
            log.error("保存角色：" + ApiCode.EXIST_ROLE_EXCEPTION.getMessage());
            throw new GlobalException(ApiCode.EXIST_ROLE_EXCEPTION);
        }
        SysRole sysRole = new SysRole();
        sysRole.setName(param.getRoleName());
        sysRole.setDescription(param.getDescription());
        return super.save(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysRole(SysRoleUpdateParam param) throws GlobalException {
        SysRole existRole = sysRoleMapper.selectOne(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getName, param.getRoleName()));
        SysRole sysRole = sysRoleMapper.selectById(param.getRoleId());
        if (Objects.nonNull(existRole) && !Objects.equals(existRole.getName(), sysRole.getName())) {
            log.error("修改角色：" + ApiCode.EXIST_ROLE_EXCEPTION.getMessage());
            throw new GlobalException(ApiCode.EXIST_ROLE_EXCEPTION);
        }
        sysRole.setName(param.getRoleName());
        sysRole.setDescription(param.getDescription());
        return super.updateById(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysRole(Integer id) throws Exception {
        //删除角色时需要删除对应的人员信息
        sysAdminRoleRelationMapper.delete(new LambdaQueryWrapper<SysAdminRoleRelation>()
                .eq(SysAdminRoleRelation::getRoleId, id));
        //删除角色对应的菜单权限
        sysRoleMenuRelationMapper.delete(new LambdaQueryWrapper<SysRoleMenuRelation>()
                .eq(SysRoleMenuRelation::getRoleId,id));
        return super.removeById(id);
    }

    @Override
    public SysRoleQueryVO getSysRoleById(Serializable id) throws Exception {
    return sysRoleMapper.getSysRoleById(id);
    }

    @Override
    public Paging<SysRolePageVO> getSysRolePageList(SysRolePageParam sysRolePageParam) throws Exception {
        sysRolePageParam.setPageSorts(null);
        Page<SysRolePageVO> page = new PageInfo<>(sysRolePageParam, null,null);
        IPage<SysRolePageVO> result = sysRoleMapper.getPageRole(page, sysRolePageParam);
        AtomicLong serNo = new AtomicLong(0);
        result.getRecords().forEach(vo -> {
            // 序号
            vo.setSerNo((sysRolePageParam.getPageIndex() - 1) * sysRolePageParam.getPageSize() + serNo.incrementAndGet());
        });
        return new Paging<SysRolePageVO>(result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateStatus(UpdateStatusParam param) {
        boolean flag = sysRoleMapper.updateStatus(param.getId(), param.getStatus());
        return flag;
    }

    @Override
    public List<SysAdminListOrgUserVO> getOrgUserList() {
        List<AllOrgInfoQueryVO> list = orgInfoMapper.getAllInfo();
        List<SysAdminListOrgUserVO> result = generateOrgInfoTree(list);
        return result;
    }

    @Override
    public List<Integer> getAlreadyOrgUser(Integer roleId) {
        List<Integer> result = sysRoleMapper.getAlreadyOrgUser(roleId);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleUser(SysRoleUserParam param) {
        List<SysAdminRoleRelation> allData = new ArrayList<>();
        for (String userIdStr : param.getUserIdArr()) {
            SysAdminRoleRelation adminRoleRelation = new SysAdminRoleRelation();
            adminRoleRelation.setRoleId(param.getRoleId());
            int userId = Integer.valueOf(userIdStr.split("-")[1]);
            adminRoleRelation.setAdminId(userId);
            allData.add(adminRoleRelation);
        }
        adminRoleRelationService.remove(new LambdaQueryWrapper<SysAdminRoleRelation>()
                .eq(SysAdminRoleRelation::getRoleId, param.getRoleId()));
        return adminRoleRelationService.saveBatch(allData);
    }

    @Override
    public void saveRolePermission(SysRolePermissionParam param) {
        sysRoleMenuRelationMapper.delete(new LambdaQueryWrapper<SysRoleMenuRelation>()
                .eq(SysRoleMenuRelation::getRoleId, param.getRoleId()));
        param.getPermissions().stream().forEach(permission -> {
            SysRoleMenuRelation roleMenuRelation = new SysRoleMenuRelation();
            roleMenuRelation.setMenuId(permission.getId());
            roleMenuRelation.setRoleId(param.getRoleId());
            if (Objects.equals(permission.getPermisOpration(), 1)) {
                roleMenuRelation.setPermission("edit");
            }
            sysRoleMenuRelationMapper.insert(roleMenuRelation);
        });
    }

    @Override
    public List<SysAdminListOrgUserVO> getNoChoiceOrgUser(Integer roleId) {
        List<AllOrgInfoQueryVO> list = orgInfoMapper.getAllInfo();
        List<SysAdminListOrgUserVO> result = generateNoChoiceOrgInfoTree(list, roleId);
        return result;
    }

    @Override
    public List<SysAdminListOrgUserVO> getAlreadyChoiceOrgUser(Integer roleId) {
        // 查询已选择的部门叶子id
        List<Long> allOrgId = sysRoleMapper.getAlreadyOrgByRoleId(roleId);
        // 查询出已选择部门的所有数据
        List<AllOrgInfoQueryVO> allOrg = new ArrayList<>();
        for (Long orgId : allOrgId) {
            List<AllOrgInfoQueryVO> treeOrg = orgInfoMapper.getOrgByChildId(orgId.intValue());
            allOrg.addAll(treeOrg);
        }
        List<AllOrgInfoQueryVO> sortList = allOrg.stream().distinct().sorted(Comparator.comparing(AllOrgInfoQueryVO::getParentId)).collect(Collectors.toList());
        List<SysAdminListOrgUserVO> result = generateAlreadyChoiceOrgInfoTree(sortList, roleId);
        return result;
    }

    private List<SysAdminListOrgUserVO> generateAlreadyChoiceOrgInfoTree(List<AllOrgInfoQueryVO> list, Integer roleId) {
        List<SysAdminListOrgUserVO> orgUserVOS = PojoUtils.convert(list, SysAdminListOrgUserVO.class);
        List<SysAdminListOrgUserVO> result = orgUserVOS.stream().filter(orgInfo -> Objects.equals(orgInfo.getParentId(), "0"))
                .map(orgInfo -> {
                    orgInfo.setChildList(getAlreadyChoiceChildrens(orgInfo, orgUserVOS, roleId));
                    return orgInfo;
                }).collect(Collectors.toList());
        return result;
    }

    private List<SysAdminListOrgUserVO> getAlreadyChoiceChildrens(SysAdminListOrgUserVO orgInfo, List<SysAdminListOrgUserVO> orgUserVOS, Integer roleId) {
        List<SysAdminListOrgUserVO> childrens = orgUserVOS.stream().filter(one -> Objects.equals(one.getParentId(), orgInfo.getId()))
                .map(one -> {
                    if (CollectionUtil.isEmpty(getAlreadyChoiceChildrens(one, orgUserVOS, roleId))) {
                        List<SysAdminListOrgUserVO> list = new ArrayList<>();
                        List<SysAdminByOrgVO> sysAdminByOrgVOS = sysRoleMapper.getAlreadyChoiceUserByOrgRole(Integer.parseInt(one.getId()), roleId);
                        sysAdminByOrgVOS.stream().forEach(info -> {
                            SysAdminListOrgUserVO vo = new SysAdminListOrgUserVO();
                            vo.setId("user-"+info.getUserId());
                            vo.setName(info.getName());
                            vo.setParentId(one.getId());
                            list.add(vo);
                        });
                        one.setChildList(list);
                    } else {
                        one.setChildList(getAlreadyChoiceChildrens(one, orgUserVOS, roleId));
                    }
                    return one;
                }).collect(Collectors.toList());
        return childrens;
    }

    private List<SysAdminListOrgUserVO> generateNoChoiceOrgInfoTree(List<AllOrgInfoQueryVO> list, Integer roleId) {
        List<SysAdminListOrgUserVO> orgUserVOS = PojoUtils.convert(list, SysAdminListOrgUserVO.class);
        List<SysAdminListOrgUserVO> result = orgUserVOS.stream().filter(orgInfo -> Objects.equals(orgInfo.getParentId(), "0"))
                .map(orgInfo -> {
                    orgInfo.setChildList(getNoChoiceChildrens(orgInfo, orgUserVOS, roleId));
                    return orgInfo;
                }).collect(Collectors.toList());
        return result;
    }

    private List<SysAdminListOrgUserVO> getNoChoiceChildrens(SysAdminListOrgUserVO orgInfo, List<SysAdminListOrgUserVO> orgUserVOS, Integer roleId) {
        List<SysAdminListOrgUserVO> childrens = orgUserVOS.stream().filter(one -> Objects.equals(one.getParentId(), orgInfo.getId()))
                .map(one -> {
                    if (CollectionUtil.isEmpty(getNoChoiceChildrens(one, orgUserVOS, roleId))) {
                        List<SysAdminListOrgUserVO> list = new ArrayList<>();
                        List<SysAdminByOrgVO> sysAdminByOrgVOS = sysRoleMapper.getNoChoiceUserByOrgRole(Integer.parseInt(one.getId()), roleId);
                        sysAdminByOrgVOS.stream().forEach(info -> {
                            SysAdminListOrgUserVO vo = new SysAdminListOrgUserVO();
                            vo.setId("user-"+info.getUserId());
                            vo.setName(info.getName());
                            vo.setParentId(one.getId());
                            list.add(vo);
                        });
                        one.setChildList(list);
                    } else {
                        one.setChildList(getNoChoiceChildrens(one, orgUserVOS, roleId));
                    }
                    return one;
                }).collect(Collectors.toList());
        return childrens;
    }


    private List<SysAdminListOrgUserVO> generateOrgInfoTree(List<AllOrgInfoQueryVO> list) {
        List<SysAdminListOrgUserVO> orgUserVOS = PojoUtils.convert(list, SysAdminListOrgUserVO.class);
        List<SysAdminListOrgUserVO> result = orgUserVOS.stream().filter(orgInfo -> Objects.equals(orgInfo.getParentId(), "0"))
                .map(orgInfo -> {
                    orgInfo.setChildList(getChildrens(orgInfo, orgUserVOS));
                    return orgInfo;
                }).collect(Collectors.toList());
        return result;
    }

    private List<SysAdminListOrgUserVO> getChildrens(SysAdminListOrgUserVO orgInfo, List<SysAdminListOrgUserVO> orgUserVOS) {
        List<SysAdminListOrgUserVO> childrens = orgUserVOS.stream().filter(one -> Objects.equals(one.getParentId(), orgInfo.getId()))
                .map(one -> {
                    if (CollectionUtil.isEmpty(getChildrens(one, orgUserVOS))) {
                        List<SysAdminListOrgUserVO> list = new ArrayList<>();
                        List<SysAdminByOrgVO> sysAdminByOrgVOS = sysRoleMapper.getUserNameByOrgId(Integer.parseInt(one.getId()));
                        sysAdminByOrgVOS.stream().forEach(info -> {
                            SysAdminListOrgUserVO vo = new SysAdminListOrgUserVO();
                            vo.setId("user-"+info.getUserId());
                            vo.setName(info.getName());
                            vo.setParentId(one.getId());
                            list.add(vo);
                        });
                        one.setChildList(list);
                    } else {
                        one.setChildList(getChildrens(one, orgUserVOS));
                    }
                    return one;
                }).collect(Collectors.toList());
        return childrens;
    }


}
