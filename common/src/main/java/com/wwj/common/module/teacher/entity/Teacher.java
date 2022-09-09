package com.wwj.common.module.teacher.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wwj.common.enums.EnabledEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * teacher
 * @author 122439195@qq.com
 */
@ApiModel(value="教师")
@Data
@TableName("teacher")
@Accessors
public class Teacher implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 教师名称
     */
    @ApiModelProperty(value="教师名称")
    private String username;


    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 联系方式
     */
    @ApiModelProperty(value="联系方式")
    private String phone;

    /**
     * 评分
     */
    @ApiModelProperty(value="评分")
    private Integer score;

    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String mail;

    /**
     * 头像
     */
    @ApiModelProperty(value="头像")
    private String icon;

    /**
     * 头像
     */
    @ApiModelProperty(value="状态：0->禁用，1->启用")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人")
    private String createUser;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value="更新人")
    private String updateUser;

    /**
     * 是否启用
     */
    @ApiModelProperty(value="是否启用")
    private EnabledEnum enabled;

    private static final long serialVersionUID = 1L;
}