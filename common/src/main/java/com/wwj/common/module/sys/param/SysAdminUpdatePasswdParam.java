package com.wwj.common.module.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname SysAdminUpdatePasswdParam
 * @Description TODO
 * @Date 2021/7/7 11:05
 * @author by ztsong
 */
@Data
public class SysAdminUpdatePasswdParam {

    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("新密码")
    private String passwd;

    @ApiModelProperty("确认新密码")
    private String confirm;

}
