package com.wwj.common.module.sys.VO.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <pre>
 *  查询结果对象
 * </pre>
 *
 * @author ztsong
 * @date 2021-07-07
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysAdminOprationLogQueryVO对象")
public class SysAdminOprationLogQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("用户id")
    private Integer adminId;

    @ApiModelProperty("操作板块")
    private String model;

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("结果")
    private String result;

    private LocalDateTime createTime;
}