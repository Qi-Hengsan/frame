package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname SysAdminRoleInfoVO
 * @Description TODO
 * @Date 2021/6/30 10:36
 * @author by ztsong
 */
@Data
public class SysAdminRoleInfoVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;
}
