package com.wwj.common.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.common.module.sys.VO.OrgInfoVO;
import com.wwj.common.module.sys.VO.query.AllOrgInfoQueryVO;
import com.wwj.common.module.sys.VO.query.OrgInfoQueryVO;
import com.wwj.common.module.sys.entity.OrgInfo;
import com.wwj.common.module.sys.param.OrgInfoPageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 组织部门信息表 Mapper 接口
 *
 * @author Xiaoqing Liu
 * @since 2021-05-26
 */
@Repository
public interface OrgInfoMapper extends BaseMapper<OrgInfo> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    OrgInfoQueryVO getOrgInfoById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param orgInfoPageParam
     * @return
     */
    IPage<OrgInfoQueryVO> getOrgInfoPageList(@Param("page") Page page
            , @Param("param") OrgInfoPageParam orgInfoPageParam);

    /**
     * 组织部门列表
     *
     * @return
     */
    List<OrgInfoQueryVO> getOrgInfoList();

    String getOrgNameByOrgId(@Param("orgId") Integer orgId);


    /**
     * 查询所有组织部门列表
     *
     * @return
     */
    List<OrgInfoVO> getOrgInfoTree();

    /**
     * 通过id查询组织部门信息
     *
     * @return
     */
    OrgInfoVO getOrgInfoVOById(Serializable id);

    /**
     * 通过用户id查询组织部门id
     */
    List<Integer> getOrgIdByUserId(Serializable id);

    /**
     * 根据子id查询所有节点
     * @param id
     * @return
     */
    List<OrgInfoQueryVO> getOrgInfoByChildId(Integer id);

    /**
     * 获取同一级别的最大编号
     * @param parentId
     * @return
     */
    String getMaxOrgNoByParentId(Integer parentId);

    /**
     *
     * @param id
     * @return
     */
    Integer getMainOrgByUserId(Integer id);

    /**
     * 返回所有数据
     * @return
     */
    List<AllOrgInfoQueryVO> getAllInfo();

    /**
     * 根据子id查询所有节点
     * @param id
     * @return
     */
    List<AllOrgInfoQueryVO> getOrgByChildId(Integer id);

    /**
     * 判断当前组织单位是否被使用
     * @param id
     * @return
     */
    Integer getOrgInfo4UsedOrNot(Integer id);

    /**
     * 判断当前组织是否有下级组织
     * @param id
     * @return
     */
    List<OrgInfoQueryVO> getOrgInfoByParentId(@Param("id") Integer id);

}