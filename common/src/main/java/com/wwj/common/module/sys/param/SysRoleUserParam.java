package com.wwj.common.module.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname SysRoleUserParam
 * @Description TODO
 * @Date 2021/7/8 11:35
 * @author by ztsong
 */
@Data
public class SysRoleUserParam {

    @ApiModelProperty("角色ID")
    private Integer roleId;

    @ApiModelProperty("用户所有ID")
    private String[] userIdArr;

}
