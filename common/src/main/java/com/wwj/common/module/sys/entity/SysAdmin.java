package com.wwj.common.module.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wwj.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * 后台用户表
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysAdmin对象")
public class SysAdmin extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建人")
    private String createUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty("更新人")
    private String updateUser;

}
