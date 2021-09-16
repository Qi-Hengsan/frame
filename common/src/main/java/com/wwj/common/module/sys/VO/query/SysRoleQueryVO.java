package com.wwj.common.module.sys.VO.query;

import com.wwj.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 后台用户角色表 查询结果对象
 * </pre>
 *
 * @author Zhutaosong
 * @date 2021-06-01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysRoleQueryVO对象")
public class SysRoleQueryVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("后台用户数量")
    private Integer adminCount;


    @ApiModelProperty("启用状态：0->禁用；1->启用")
    private Integer status;

    private Integer sort;
}