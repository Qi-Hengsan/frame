package com.wwj.common.module.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 阿涛
 * @date 2021-06-01
 */
@Data
public class LoginParam {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
