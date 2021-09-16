package com.wwj.common.module.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * 
 *
 * @author ztsong
 * @since 2021-07-07
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysAdminOperationLog对象")
public class SysAdminOperationLog {
    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @ApiModelProperty("用户id")
    private Integer adminId;


    @ApiModelProperty("操作板块")
    private String model;


    @ApiModelProperty("ip地址")
    private String ip;


    @ApiModelProperty("内容")
    private String content;


    @ApiModelProperty("操作结果")
    private String result;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
