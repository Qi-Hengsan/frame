package com.wwj.common.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.common.module.sys.VO.query.SysAdminRoleRelationQueryVO;
import com.wwj.common.module.sys.entity.SysAdminRoleRelation;
import com.wwj.common.module.sys.param.SysAdminRoleRelationPageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 后台用户和角色关系表 Mapper 接口
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Repository
public interface SysAdminRoleRelationMapper extends BaseMapper<SysAdminRoleRelation> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysAdminRoleRelationQueryVO getSysAdminRoleRelationById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysAdminRoleRelationPageParam
     * @return
     */
    IPage<SysAdminRoleRelationQueryVO> getSysAdminRoleRelationPageList(@Param("page") Page page
        , @Param("param") SysAdminRoleRelationPageParam sysAdminRoleRelationPageParam);

}
