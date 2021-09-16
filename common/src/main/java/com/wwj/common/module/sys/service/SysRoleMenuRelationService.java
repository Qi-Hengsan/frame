package com.wwj.common.module.sys.service;


import com.wwj.core.pagination.Paging;
import com.wwj.core.service.BaseService;
import com.wwj.common.module.sys.VO.query.SysRoleMenuRelationQueryVO;
import com.wwj.common.module.sys.entity.SysRoleMenuRelation;
import com.wwj.common.module.sys.param.SysRoleMenuRelationPageParam;

import java.io.Serializable;

/**
 * 后台角色菜单关系表 服务类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
public interface SysRoleMenuRelationService extends BaseService<SysRoleMenuRelation> {

    /**
     * 保存
     *
     * @param sysRoleMenuRelation
     * @return
     * @throws Exception
     */
    boolean saveSysRoleMenuRelation(SysRoleMenuRelation sysRoleMenuRelation) throws Exception;

    /**
     * 修改
     *
     * @param sysRoleMenuRelation
     * @return
     * @throws Exception
     */
    boolean updateSysRoleMenuRelation(SysRoleMenuRelation sysRoleMenuRelation) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysRoleMenuRelation(Integer id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysRoleMenuRelationQueryVO getSysRoleMenuRelationById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysRoleMenuRelationPageParam
     * @return
     * @throws Exception
     */
    Paging<SysRoleMenuRelationQueryVO> getSysRoleMenuRelationPageList(
        SysRoleMenuRelationPageParam sysRoleMenuRelationPageParam) throws Exception;

}
