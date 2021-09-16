package com.wwj.common.module.sys.service;


import com.wwj.core.exception.GlobalException;
import com.wwj.core.pagination.Paging;
import com.wwj.core.service.BaseService;
import com.wwj.common.module.sys.VO.SysAdminListOrgUserVO;
import com.wwj.common.module.sys.VO.query.SysRolePageVO;
import com.wwj.common.module.sys.VO.query.SysRoleQueryVO;
import com.wwj.common.module.sys.entity.SysRole;
import com.wwj.common.module.sys.param.*;

import java.io.Serializable;
import java.util.List;

/**
 * 后台用户角色表 服务类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 保存
     *
     * @return
     * @throws Exception
     */
    boolean saveSysRole(SysRoleAddParam param) throws GlobalException;

    /**
     * 修改
     * @return
     * @throws Exception
     */
    boolean updateSysRole(SysRoleUpdateParam param) throws GlobalException;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysRole(Integer id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysRoleQueryVO getSysRoleById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysRolePageParam
     * @return
     * @throws Exception
     */
    Paging<SysRolePageVO> getSysRolePageList(
        SysRolePageParam sysRolePageParam) throws Exception;

    /**
     * 启用状态修改
     * @return
     */
    boolean updateStatus(UpdateStatusParam param);

    /**
     * 角色权限-添加人员-部门员工数据
     * @return
     */
    List<SysAdminListOrgUserVO> getOrgUserList();

    /**
     * 已选择员工
     * @return
     */
    List<Integer> getAlreadyOrgUser(Integer roleId);

    /**
     * 角色添加人员
     * @param param
     * @return
     */
    boolean saveRoleUser(SysRoleUserParam param);

    /**
     * 保存权限设置
     * @param param
     * @return
     */
    void saveRolePermission(SysRolePermissionParam param);

    /**
     * 角色权限-添加人员-未选择的部门员工
     * @param roleId
     * @return
     */
    List<SysAdminListOrgUserVO> getNoChoiceOrgUser(Integer roleId);

    /**
     * 角色权限-添加人员-已选择的部门员工
     * @param roleId
     * @return
     */
    List<SysAdminListOrgUserVO> getAlreadyChoiceOrgUser(Integer roleId);
}
