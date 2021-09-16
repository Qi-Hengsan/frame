package com.wwj.common.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.common.module.sys.VO.OperationLogVO;
import com.wwj.common.module.sys.VO.query.SysAdminOprationLogQueryVO;
import com.wwj.common.module.sys.entity.SysAdminOperationLog;
import com.wwj.common.module.sys.param.SysAdminOperationLogPageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 *  Mapper 接口
 *
 * @author ztsong
 * @since 2021-07-07
 */
@Repository
public interface SysAdminOperationLogMapper extends BaseMapper<SysAdminOperationLog> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysAdminOprationLogQueryVO getSysAdminOperationLogById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param sysAdminOperationLogPageParam
     * @return
     */
    IPage<OperationLogVO> getSysAdminOperationLogPageList(@Param("page") Page page
        , @Param("param") SysAdminOperationLogPageParam sysAdminOperationLogPageParam);

}
