package com.wwj.common.module.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname UpdateStatusParam
 * @Description TODO
 * @Date 2021/7/8 12:01
 * @author by ztsong
 */
@Data
public class UpdateStatusParam {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("状态：0->禁用，1->启用")
    private Integer status;

}
