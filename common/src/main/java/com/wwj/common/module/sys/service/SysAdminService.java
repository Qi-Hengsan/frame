package com.wwj.common.module.sys.service;


import com.wwj.core.pagination.Paging;
import com.wwj.core.service.BaseService;
import com.wwj.common.module.sys.VO.*;
import com.wwj.common.module.sys.domain.AdminUserDetails;
import com.wwj.common.module.sys.entity.SysAdmin;
import com.wwj.common.module.sys.param.SysAdminBaseParam;
import com.wwj.common.module.sys.param.SysAdminPageParam;
import com.wwj.common.module.sys.param.SysAdminUpdatePasswdParam;
import com.wwj.common.module.sys.param.UpdateStatusParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 后台用户表 服务类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
public interface SysAdminService extends BaseService<SysAdmin> {

    /**
     * 保存
     *
     * @return
     * @throws Exception
     */
    int saveSysAdmin(SysAdminBaseParam param) throws Exception;

    /**
     * 修改
     *
     * @param sysAdmin
     * @return
     * @throws Exception
     */
    boolean updateSysAdmin(SysAdminBaseParam sysAdmin) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysAdmin(Integer id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysAdminInfoVO getSysAdminById(Integer id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysAdminPageParam
     * @return
     * @throws Exception
     */
    Paging<SysAdminInfoVO> getSysAdminPageList(
        SysAdminPageParam sysAdminPageParam) throws Exception;

    /**
     * 根据用户名获取后台管理员
     */
    SysAdmin getAdminByUsername(String username);

    /**
     * 获取用户信息
     */
    AdminUserDetails loadUserByUsername(String username);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     */
    LoginUserInfoVO login(String username, String password);

    /**
     * 获取用户名称
     * @param id
     * @return
     */
    String getUserName(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    List<SysAdminVO> getAllUserList();

    /**
     * 用户添加所属部门下拉
     * @return
     */
    List<SysAdminListOrgInfoVO> listOrgInfo();

    /**
     * 用户添加所属角色下拉
     * @return
     */
    List<SysAdminRoleInfoVO> listRoleInfo();

    /**
     * 修改用户状态
     * @return
     */
    boolean updateStatus(UpdateStatusParam param);

    /**
     * 修改密码
     * @return
     */
    boolean updatePassword(SysAdminUpdatePasswdParam passwdParam);

    /**
     * 更新用户获取数据
     * @param id
     * @return
     */
    SysUpdateAdminInfoVO getUpdateAdminInfo(Integer id);

    /**
     * 刷新token
     * @param request
     * @return
     */
    LoginUserInfoVO refreshToken(HttpServletRequest request);

    /**
     * 获取用户姓名
     * @param userId
     * @return
     */
    String getName(Integer userId);
}
