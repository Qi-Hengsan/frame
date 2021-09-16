package com.wwj.common.module.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Classname SysRolePermissionParam
 * @Description TODO
 * @Date 2021/7/13 16:53
 * @author by ztsong
 */
@Data
public class SysRolePermissionParam {

    private List<Permission> permissions;

    private int roleId;

    @Getter
    @Setter
    public static class Permission {
        @ApiModelProperty("菜单id")
        private int id;
        @ApiModelProperty("是否有操作权限")
        private int permisOpration;
    }

}
