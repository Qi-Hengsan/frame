package com.wwj.common.module.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.core.pagination.PageInfo;
import com.wwj.core.pagination.Paging;
import com.wwj.core.service.impl.BaseServiceImpl;
import com.wwj.common.module.sys.VO.query.SysAdminRoleRelationQueryVO;
import com.wwj.common.module.sys.entity.SysAdminRoleRelation;
import com.wwj.common.module.sys.mapper.SysAdminRoleRelationMapper;
import com.wwj.common.module.sys.param.SysAdminRoleRelationPageParam;
import com.wwj.common.module.sys.service.SysAdminRoleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * 后台用户和角色关系表 服务实现类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Slf4j
@Service
public class SysAdminRoleRelationServiceImpl
    extends BaseServiceImpl<SysAdminRoleRelationMapper, SysAdminRoleRelation> implements SysAdminRoleRelationService {

    @Autowired
    private SysAdminRoleRelationMapper sysAdminRoleRelationMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysAdminRoleRelation(SysAdminRoleRelation sysAdminRoleRelation) throws Exception {
        return super.save(sysAdminRoleRelation);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysAdminRoleRelation(SysAdminRoleRelation sysAdminRoleRelation) throws Exception {
        return super.updateById(sysAdminRoleRelation);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysAdminRoleRelation(Integer id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public SysAdminRoleRelationQueryVO getSysAdminRoleRelationById(Serializable id) throws Exception {
    return sysAdminRoleRelationMapper.getSysAdminRoleRelationById(id);
    }

    @Override
    public Paging<SysAdminRoleRelationQueryVO> getSysAdminRoleRelationPageList(SysAdminRoleRelationPageParam sysAdminRoleRelationPageParam) throws Exception {
        Page<SysAdminRoleRelationQueryVO> page = new PageInfo<>(sysAdminRoleRelationPageParam, null,null);
        IPage<SysAdminRoleRelationQueryVO> iPage = sysAdminRoleRelationMapper.getSysAdminRoleRelationPageList(page, sysAdminRoleRelationPageParam);
        return new Paging<SysAdminRoleRelationQueryVO>(iPage);
    }

}
