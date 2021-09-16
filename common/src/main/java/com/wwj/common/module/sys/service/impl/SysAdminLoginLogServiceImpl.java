package com.wwj.common.module.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.core.pagination.PageInfo;
import com.wwj.core.pagination.Paging;
import com.wwj.core.service.impl.BaseServiceImpl;
import com.wwj.common.module.sys.VO.LoginLogVO;
import com.wwj.common.module.sys.entity.SysAdminLoginLog;
import com.wwj.common.module.sys.mapper.SysAdminLoginLogMapper;
import com.wwj.common.module.sys.param.SysAdminLoginLogPageParam;
import com.wwj.common.module.sys.service.SysAdminLoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 后台用户登录日志表 服务实现类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Slf4j
@Service
public class SysAdminLoginLogServiceImpl
    extends BaseServiceImpl<SysAdminLoginLogMapper, SysAdminLoginLog> implements SysAdminLoginLogService {

    @Autowired
    private SysAdminLoginLogMapper sysAdminLoginLogMapper;

    @Override
    public Paging<LoginLogVO> getSysAdminLoginLogPageList(SysAdminLoginLogPageParam param) throws Exception {
        Page<LoginLogVO> page = new PageInfo<>(param, null,null);
        IPage<LoginLogVO> result = sysAdminLoginLogMapper.getSysAdminLoginLogPageList(page, param);
        AtomicLong serNo = new AtomicLong(0);
        result.getRecords().forEach(vo -> {
            vo.setSerNo((param.getPageIndex() - 1) * param.getPageSize() + serNo.incrementAndGet());
        });
        return new Paging<LoginLogVO>(result);
    }

}
