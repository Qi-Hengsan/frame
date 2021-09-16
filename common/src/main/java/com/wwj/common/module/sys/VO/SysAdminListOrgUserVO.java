package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname SysAdminListOrgUserVO
 * @Description TODO
 * @Date 2021/7/7 16:57
 * @author by ztsong
 */
@Data
public class SysAdminListOrgUserVO {
    @ApiModelProperty("部门id")
    private String id;

    @ApiModelProperty("组织名")
    private String name;

    @ApiModelProperty("部门父ID")
    private String parentId;

    @ApiModelProperty("子数据")
    private List<SysAdminListOrgUserVO> childList;
}
