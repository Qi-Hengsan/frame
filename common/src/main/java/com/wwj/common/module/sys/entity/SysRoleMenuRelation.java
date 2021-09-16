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
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * 后台角色菜单关系表
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysRoleMenuRelation对象")
public class SysRoleMenuRelation extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色ID")
    private Integer roleId;

    @ApiModelProperty("菜单ID")
    private Integer menuId;

    @ApiModelProperty("菜单ID")
    private String permission;

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
