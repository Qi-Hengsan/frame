package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname SysUpdateAdminInfoVO
 * @Description TODO
 * @Date 2021/7/8 11:51
 * @author by ztsong
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "修改用户信息")
public class SysUpdateAdminInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("性别：0->男；1->女")
    private Integer sex;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("所属部门")
    private List<Integer> department;

    @ApiModelProperty("所属角色")
    private List<Integer> role;

}
