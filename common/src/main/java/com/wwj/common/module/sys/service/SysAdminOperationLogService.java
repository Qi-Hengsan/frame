package com.wwj.common.module.sys.service;


import com.wwj.core.pagination.Paging;
import com.wwj.core.service.BaseService;
import com.wwj.common.module.sys.VO.OperationLogVO;
import com.wwj.common.module.sys.VO.OprationLogSelectVO;
import com.wwj.common.module.sys.entity.SysAdminOperationLog;
import com.wwj.common.module.sys.param.SysAdminOperationLogPageParam;

import java.util.List;

/**
 *  服务类
 *
 * @author ztsong
 * @since 2021-07-07
 */
public interface SysAdminOperationLogService extends BaseService<SysAdminOperationLog> {

    /**
     * 获取分页对象
     *
     * @param sysAdminOperationLogPageParam
     * @return
     * @throws Exception
     */
    Paging<OperationLogVO> getSysAdminOperationLogPageList(
        SysAdminOperationLogPageParam sysAdminOperationLogPageParam) throws Exception;

    /**
     * 返回所有二级模块下拉
     * @return
     */
    List<OprationLogSelectVO> getModel();
}
