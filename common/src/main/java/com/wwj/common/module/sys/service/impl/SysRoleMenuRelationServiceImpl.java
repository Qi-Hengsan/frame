package com.wwj.common.module.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.core.pagination.PageInfo;
import com.wwj.core.pagination.Paging;
import com.wwj.core.service.impl.BaseServiceImpl;
import com.wwj.common.module.sys.VO.query.SysRoleMenuRelationQueryVO;
import com.wwj.common.module.sys.entity.SysRoleMenuRelation;
import com.wwj.common.module.sys.mapper.SysRoleMenuRelationMapper;
import com.wwj.common.module.sys.param.SysRoleMenuRelationPageParam;
import com.wwj.common.module.sys.service.SysRoleMenuRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * 后台角色菜单关系表 服务实现类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Slf4j
@Service
public class SysRoleMenuRelationServiceImpl
    extends BaseServiceImpl<SysRoleMenuRelationMapper, SysRoleMenuRelation> implements SysRoleMenuRelationService {

    @Autowired
    private SysRoleMenuRelationMapper sysRoleMenuRelationMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysRoleMenuRelation(SysRoleMenuRelation sysRoleMenuRelation) throws Exception {
        return super.save(sysRoleMenuRelation);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysRoleMenuRelation(SysRoleMenuRelation sysRoleMenuRelation) throws Exception {
        return super.updateById(sysRoleMenuRelation);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysRoleMenuRelation(Integer id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public SysRoleMenuRelationQueryVO getSysRoleMenuRelationById(Serializable id) throws Exception {
    return sysRoleMenuRelationMapper.getSysRoleMenuRelationById(id);
    }

    @Override
    public Paging<SysRoleMenuRelationQueryVO> getSysRoleMenuRelationPageList(SysRoleMenuRelationPageParam sysRoleMenuRelationPageParam) throws Exception {
        Page<SysRoleMenuRelationQueryVO> page = new PageInfo<>(sysRoleMenuRelationPageParam, null,null);
        IPage<SysRoleMenuRelationQueryVO> iPage = sysRoleMenuRelationMapper.getSysRoleMenuRelationPageList(page, sysRoleMenuRelationPageParam);
        return new Paging<SysRoleMenuRelationQueryVO>(iPage);
    }

}
