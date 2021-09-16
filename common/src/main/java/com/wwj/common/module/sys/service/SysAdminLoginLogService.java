package com.wwj.common.module.sys.service;


import com.wwj.common.module.sys.VO.LoginLogVO;
import com.wwj.common.module.sys.entity.SysAdminLoginLog;
import com.wwj.common.module.sys.param.SysAdminLoginLogPageParam;
import com.wwj.core.pagination.Paging;
import com.wwj.core.service.BaseService;

/**
 * 后台用户登录日志表 服务类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
public interface SysAdminLoginLogService extends BaseService<SysAdminLoginLog> {

    /**
     * 获取分页对象
     *
     * @param sysAdminLoginLogPageParam
     * @return
     * @throws Exception
     */
    Paging<LoginLogVO> getSysAdminLoginLogPageList(
        SysAdminLoginLogPageParam sysAdminLoginLogPageParam) throws Exception;

}
