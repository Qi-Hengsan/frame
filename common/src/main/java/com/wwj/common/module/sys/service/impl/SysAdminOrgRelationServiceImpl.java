package com.wwj.common.module.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.core.pagination.PageInfo;
import com.wwj.core.pagination.Paging;
import com.wwj.core.service.impl.BaseServiceImpl;
import com.wwj.common.module.sys.VO.SysAdminOrgRelationQueryVO;
import com.wwj.common.module.sys.entity.SysAdminOrgRelation;
import com.wwj.common.module.sys.mapper.SysAdminOrgRelationMapper;
import com.wwj.common.module.sys.param.SysAdminOrgRelationPageParam;
import com.wwj.common.module.sys.service.SysAdminOrgRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 *  服务实现类
 *
 * @author ztsong
 * @since 2021-06-29
 */
@Slf4j
@Service
public class SysAdminOrgRelationServiceImpl
    extends BaseServiceImpl<SysAdminOrgRelationMapper, SysAdminOrgRelation> implements SysAdminOrgRelationService {

    @Autowired
    private SysAdminOrgRelationMapper sysAdminOrgRelationMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysAdminOrgRelation(SysAdminOrgRelation sysAdminOrgRelation) throws Exception {
        return super.save(sysAdminOrgRelation);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysAdminOrgRelation(SysAdminOrgRelation sysAdminOrgRelation) throws Exception {
        return super.updateById(sysAdminOrgRelation);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysAdminOrgRelation(Integer id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public SysAdminOrgRelationQueryVO getSysAdminOrgRelationById(Serializable id) throws Exception {
    return sysAdminOrgRelationMapper.getSysAdminOrgRelationById(id);
    }

    @Override
    public Paging<SysAdminOrgRelationQueryVO> getSysAdminOrgRelationPageList(SysAdminOrgRelationPageParam sysAdminOrgRelationPageParam) throws Exception {
        Page<SysAdminOrgRelationQueryVO> page = new PageInfo<>(sysAdminOrgRelationPageParam, null,null);
        IPage<SysAdminOrgRelationQueryVO> iPage = sysAdminOrgRelationMapper.getSysAdminOrgRelationPageList(page, sysAdminOrgRelationPageParam);
        return new Paging<SysAdminOrgRelationQueryVO>(iPage);
    }

}
