package com.wwj.common.module.sys.service;


import com.wwj.core.pagination.Paging;
import com.wwj.core.service.BaseService;
import com.wwj.common.module.sys.VO.SysAdminOrgRelationQueryVO;
import com.wwj.common.module.sys.entity.SysAdminOrgRelation;
import com.wwj.common.module.sys.param.SysAdminOrgRelationPageParam;

import java.io.Serializable;

/**
 *  服务类
 *
 * @author ztsong
 * @since 2021-06-29
 */
public interface SysAdminOrgRelationService extends BaseService<SysAdminOrgRelation> {

    /**
     * 保存
     *
     * @param sysAdminOrgRelation
     * @return
     * @throws Exception
     */
    boolean saveSysAdminOrgRelation(SysAdminOrgRelation sysAdminOrgRelation) throws Exception;

    /**
     * 修改
     *
     * @param sysAdminOrgRelation
     * @return
     * @throws Exception
     */
    boolean updateSysAdminOrgRelation(SysAdminOrgRelation sysAdminOrgRelation) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysAdminOrgRelation(Integer id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysAdminOrgRelationQueryVO getSysAdminOrgRelationById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysAdminOrgRelationPageParam
     * @return
     * @throws Exception
     */
    Paging<SysAdminOrgRelationQueryVO> getSysAdminOrgRelationPageList(
        SysAdminOrgRelationPageParam sysAdminOrgRelationPageParam) throws Exception;

}
