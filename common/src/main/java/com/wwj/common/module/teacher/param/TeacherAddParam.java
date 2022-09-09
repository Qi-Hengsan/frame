package com.wwj.common.module.teacher.param;

import com.wwj.common.enums.EnabledEnum;
import com.wwj.common.module.teacher.entity.Teacher;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wushilin
 * @version 1.0
 * @date 2021/08/19 16:42:16
 */
@ApiModel(value = "教师新增")
@Data
public class TeacherAddParam extends Teacher {


    /**
     * 是否启用
     */
    @ApiModelProperty(value="是否启用")
    private EnabledEnum enabled;
}
