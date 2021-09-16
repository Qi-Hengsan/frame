package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname SysAdminListOrgInfoVO
 * @Description TODO
 * @Date 2021/6/30 9:27
 * @author by ztsong
 */
@Data
public class SysAdminListOrgInfoVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("组织名")
    private String orgName;

    @ApiModelProperty("父ID")
    private Integer parentId;

    @ApiModelProperty("子数据")
    private List<SysAdminListOrgInfoVO> childList;

}
