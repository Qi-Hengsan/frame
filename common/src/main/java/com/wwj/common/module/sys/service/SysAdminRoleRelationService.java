package com.wwj.common.module.sys.service;


import com.wwj.core.pagination.Paging;
import com.wwj.core.service.BaseService;
import com.wwj.common.module.sys.VO.query.SysAdminRoleRelationQueryVO;
import com.wwj.common.module.sys.entity.SysAdminRoleRelation;
import com.wwj.common.module.sys.param.SysAdminRoleRelationPageParam;

import java.io.Serializable;

/**
 * 后台用户和角色关系表 服务类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
public interface SysAdminRoleRelationService extends BaseService<SysAdminRoleRelation> {

    /**
     * 保存
     *
     * @param sysAdminRoleRelation
     * @return
     * @throws Exception
     */
    boolean saveSysAdminRoleRelation(SysAdminRoleRelation sysAdminRoleRelation) throws Exception;

    /**
     * 修改
     *
     * @param sysAdminRoleRelation
     * @return
     * @throws Exception
     */
    boolean updateSysAdminRoleRelation(SysAdminRoleRelation sysAdminRoleRelation) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysAdminRoleRelation(Integer id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysAdminRoleRelationQueryVO getSysAdminRoleRelationById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysAdminRoleRelationPageParam
     * @return
     * @throws Exception
     */
    Paging<SysAdminRoleRelationQueryVO> getSysAdminRoleRelationPageList(
        SysAdminRoleRelationPageParam sysAdminRoleRelationPageParam) throws Exception;

}
