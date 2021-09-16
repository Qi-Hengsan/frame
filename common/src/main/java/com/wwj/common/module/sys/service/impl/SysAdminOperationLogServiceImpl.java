package com.wwj.common.module.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.core.pagination.PageInfo;
import com.wwj.core.pagination.Paging;
import com.wwj.core.service.impl.BaseServiceImpl;
import com.wwj.common.module.sys.VO.OperationLogVO;
import com.wwj.common.module.sys.VO.OprationLogSelectVO;
import com.wwj.common.module.sys.entity.SysAdminOperationLog;
import com.wwj.common.module.sys.mapper.SysAdminOperationLogMapper;
import com.wwj.common.module.sys.mapper.SysMenuMapper;
import com.wwj.common.module.sys.param.SysAdminOperationLogPageParam;
import com.wwj.common.module.sys.service.SysAdminOperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *  服务实现类
 *
 * @author ztsong
 * @since 2021-07-07
 */
@Slf4j
@Service
public class SysAdminOperationLogServiceImpl
    extends BaseServiceImpl<SysAdminOperationLogMapper, SysAdminOperationLog> implements SysAdminOperationLogService {

    @Resource
    private SysAdminOperationLogMapper sysAdminOperationLogMapper;

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public Paging<OperationLogVO> getSysAdminOperationLogPageList(SysAdminOperationLogPageParam param) throws Exception {
        Page<OperationLogVO> page = new PageInfo<>(param, null,null);
        IPage<OperationLogVO> result = sysAdminOperationLogMapper.getSysAdminOperationLogPageList(page, param);
        AtomicLong serNo = new AtomicLong(0);
        result.getRecords().forEach(vo -> {
            vo.setSerNo((param.getPageIndex() - 1) * param.getPageSize() + serNo.incrementAndGet());
        });
        return new Paging<>(result);
    }

    @Override
    public List<OprationLogSelectVO> getModel() {
        return sysMenuMapper.getOprationLogSelect();
    }

}
