package com.wwj.common.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.common.module.sys.VO.query.SysRoleMenuRelationQueryVO;
import com.wwj.common.module.sys.entity.SysRoleMenuRelation;
import com.wwj.common.module.sys.param.SysRoleMenuRelationPageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 后台角色菜单关系表 Mapper 接口
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Repository
public interface SysRoleMenuRelationMapper extends BaseMapper<SysRoleMenuRelation> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysRoleMenuRelationQueryVO getSysRoleMenuRelationById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysRoleMenuRelationPageParam
     * @return
     */
    IPage<SysRoleMenuRelationQueryVO> getSysRoleMenuRelationPageList(@Param("page") Page page
        , @Param("param") SysRoleMenuRelationPageParam sysRoleMenuRelationPageParam);

}
