package com.wwj.common.module.teacher.param;

import com.wwj.core.pagination.BasePageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 122439195@qq.com
 * @version 1.0
 * @date 2021/08/18 17:05:36
 */
@ApiModel("教师列表分页查询参数")
@Data
public class TeacherPageParam extends BasePageParam {

    /**
     * 状态：0->禁用，1->启用
     */
    @ApiModelProperty(value="状态：0->禁用，1->启用")
    private Integer status;

    /**
     * 教师名称
     */
    @ApiModelProperty(value="教师名称")
    private String name;

    /**
     * 联系方式
     */
    @ApiModelProperty(value="联系方式")
    private String phone;
}
