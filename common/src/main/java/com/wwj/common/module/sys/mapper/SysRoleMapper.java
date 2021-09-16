package com.wwj.common.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.common.module.sys.VO.RoleNumVO;
import com.wwj.common.module.sys.VO.SysAdminByOrgVO;
import com.wwj.common.module.sys.VO.query.SysRolePageVO;
import com.wwj.common.module.sys.VO.query.SysRoleQueryVO;
import com.wwj.common.module.sys.entity.SysRole;
import com.wwj.common.module.sys.param.SysRolePageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 后台用户角色表 Mapper 接口
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysRoleQueryVO getSysRoleById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysRolePageParam
     * @return
     */
    IPage<SysRolePageVO> getPageRole(@Param("page") Page page
        , @Param("param") SysRolePageParam sysRolePageParam);

    /**
     * 获取用户所有角色
     */
    List<SysRole> getRoleList(@Param("adminId") Integer adminId);

    /**
     * 获取所有角色
     */
    List<SysRole> getAllRoleList();

    /**
     * 获取角色人数
     * @return
     */
    List<RoleNumVO> getRoleNum();

    /**
     * 修改启用状态
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 根据orgId获取用户名和用户Id
     * @param id
     * @return
     */
    List<SysAdminByOrgVO> getUserNameByOrgId(@Param("id") Integer id);

    List<Integer> getAlreadyOrgUser(@Param("id") Integer id);

    /**
     * 根据部门id和角色id查询用户
     * @return
     */
    List<SysAdminByOrgVO> getNoChoiceUserByOrgRole(@Param("orgId") Integer orgId, @Param("roleId") Integer roleId);

    /**
     * 根据角色id获取已选择的的部门id
     * @param roleId
     * @return
     */
    List<Long> getAlreadyOrgByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色id获取未选择的的部门id
     * @return
     */
    List<SysAdminByOrgVO> getAlreadyChoiceUserByOrgRole(@Param("orgId") Integer orgId, @Param("roleId") Integer roleId);

    /**
     * 根据用户id查询角色是否被禁用
     * @param userId
     * @return
     */
    int getStatusByUserId(@Param("userId") Integer userId);
}
