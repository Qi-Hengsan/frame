package com.wwj.common.module.sys.VO.query;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Classname SysRolePageVO
 * @Description TODO
 * @Date 2021/6/29 11:48
 * @author by ztsong
 */
@Data
@ApiModel(value = "SysRolePageVO对象")
public class SysRolePageVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @ExcelProperty("序号")
    private Long serNo;

    @ApiModelProperty("角色ID")
    @ExcelIgnore
    private Integer roleId;

    @ApiModelProperty("角色名称")
    @ExcelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色人员")
    @ExcelProperty("角色人员")
    private Integer adminCount;

    @ApiModelProperty("角色描述")
    @ExcelProperty("角色描述")
    private String description;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("启用状态")
    @ExcelIgnore
    private Integer status;

    @ApiModelProperty("启用状态")
    @ExcelProperty("启用状态")
    private String statusName;
}
