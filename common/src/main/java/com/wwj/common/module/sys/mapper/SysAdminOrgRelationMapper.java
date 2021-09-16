package com.wwj.common.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.common.module.sys.VO.SysAdminOrgRelationQueryVO;
import com.wwj.common.module.sys.entity.SysAdminOrgRelation;
import com.wwj.common.module.sys.param.SysAdminOrgRelationPageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 *  Mapper 接口
 *
 * @author ztsong
 * @since 2021-06-29
 */
@Repository
public interface SysAdminOrgRelationMapper extends BaseMapper<SysAdminOrgRelation> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysAdminOrgRelationQueryVO getSysAdminOrgRelationById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysAdminOrgRelationPageParam
     * @return
     */
    IPage<SysAdminOrgRelationQueryVO> getSysAdminOrgRelationPageList(@Param("page") Page page
        , @Param("param") SysAdminOrgRelationPageParam sysAdminOrgRelationPageParam);

}
