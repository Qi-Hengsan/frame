package com.wwj.common.module.teacher.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wushilin
 * @version 1.0
 * @date 2021/08/19 16:42:16
 */
@ApiModel(value = "代理商批量修改请求参数")
@Data
public class TeacherUpdateParam {

    @ApiModelProperty(value = "id")
    private List<Integer> ids;

    @ApiModelProperty(value = "状态：0->禁用，1->启用 ")
    private Integer status;
}
