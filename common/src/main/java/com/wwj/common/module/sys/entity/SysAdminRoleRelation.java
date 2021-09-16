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
 * 后台用户和角色关系表
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysAdminRoleRelation对象")
public class SysAdminRoleRelation extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer adminId;

    private Integer roleId;

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
