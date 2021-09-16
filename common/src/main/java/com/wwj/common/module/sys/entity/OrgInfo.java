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
 * 组织部门信息表
 *
 * @author Xiaoqing Liu
 * @since 2021-05-26
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OrgInfo对象")
public class OrgInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("组织名称：公司或者部门名称")
    private String orgName;

    @ApiModelProperty("组织简称")
    private String orgShort;

    @ApiModelProperty("组织编号")
    private String orgNo;

    @ApiModelProperty("上级组织")
    private Integer parentId;

    @ApiModelProperty("说明")
    private String des;

    @ApiModelProperty("启用状态：1（true）是 0（false）否")
    private Boolean isEnable;

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
