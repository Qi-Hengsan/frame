package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname Login
 * @Description TODO
 * @Date 2021/7/9 10:18
 * @author by ztsong
 */
@Data
public class LoginUserInfoVO implements Serializable {

    @ApiModelProperty("tokenHead")
    private String tokenHead;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("姓名")
    private String name;

}
