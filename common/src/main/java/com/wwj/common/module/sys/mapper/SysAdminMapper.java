package com.wwj.common.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.common.module.sys.VO.query.SysAdminQueryVO;
import com.wwj.common.module.sys.entity.SysAdmin;
import com.wwj.common.module.sys.param.SysAdminPageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 后台用户表 Mapper 接口
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Repository
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysAdminQueryVO getSysAdminById(Integer id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysAdminPageParam
     * @return
     */
    IPage<SysAdminQueryVO> getSysAdminPageList(@Param("page") Page page
        , @Param("param") SysAdminPageParam sysAdminPageParam);

    /**
     * 获取用户名
     * @param id
     * @return
     */
    String getUserNameById(@Param("id") Integer id);

    /**
     * 更新用户状态
     * @param id
     */
    boolean updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 通过身份证号查询用户信息
     * @param idCard
     * @return
     */
    SysAdminQueryVO getSysAdminByIdCard(@Param("idCard") String idCard);

    /**
     * 获取用户姓名
     * @param userId
     * @return
     */
    String getName(@Param("userId") Integer userId);
}
