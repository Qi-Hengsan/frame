package com.wwj.common.module.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname SysRoleBaseParam
 * @Description TODO
 * @Date 2021/7/1 18:23
 * @author by ztsong
 */
@Data
public class SysRoleUpdateParam {

    @ApiModelProperty(value = "角色Id", required = true)
    private Integer roleId;

    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

    @ApiModelProperty(value = "角色描述", required = false)
    private String description;

}
