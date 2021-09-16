package com.wwj.common.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.common.module.sys.VO.LoginLogVO;
import com.wwj.common.module.sys.VO.query.SysAdminLoginLogQueryVO;
import com.wwj.common.module.sys.entity.SysAdminLoginLog;
import com.wwj.common.module.sys.param.SysAdminLoginLogPageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 后台用户登录日志表 Mapper 接口
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Repository
public interface SysAdminLoginLogMapper extends BaseMapper<SysAdminLoginLog> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    SysAdminLoginLogQueryVO getSysAdminLoginLogById(Serializable id);

    /**
     * 获取分页对象
     *
     * @return
     */
    IPage<LoginLogVO> getSysAdminLoginLogPageList(@Param("page") Page page
        , @Param("param") SysAdminLoginLogPageParam sysAdminLoginLogPageParam);

}
