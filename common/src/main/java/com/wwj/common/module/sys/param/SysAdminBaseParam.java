package com.wwj.common.module.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Classname SysAdminAddParam
 * @Description TODO
 * @Date 2021/6/29 16:54
 * @author by ztsong
 */
@Data
@ToString
public class SysAdminBaseParam {

    @ApiModelProperty(value = "id", required = false)
    private Integer id;

    @ApiModelProperty("登录账号")
    private String username;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("性别：0->男；1->女")
    private Integer sex;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("所属部门")
    private int[]  department;

    @ApiModelProperty("所属角色")
    private int[]  role;

}
