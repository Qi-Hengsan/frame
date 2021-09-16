package com.wwj.common.module.sys.VO.query;

import com.wwj.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <pre>
 * 后台用户表 查询结果对象
 * </pre>
 *
 * @author Zhutaosong
 * @date 2021-06-01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysAdminQueryVO对象")
public class SysAdminQueryVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像")
    private String icon;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("性别：0->男；1->女")
    private Integer sex;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("备注信息")
    private String note;

    @ApiModelProperty("最后登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty("帐号启用状态：0->禁用；1->启用")
    private Integer status;

}