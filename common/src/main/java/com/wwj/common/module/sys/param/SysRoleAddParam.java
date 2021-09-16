package com.wwj.common.module.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname SysRoleAddParam
 * @Description TODO
 * @Date 2021/7/20 16:01
 * @author by ztsong
 */
@Data
public class SysRoleAddParam {

    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

    @ApiModelProperty(value = "角色描述", required = false)
    private String description;

}
