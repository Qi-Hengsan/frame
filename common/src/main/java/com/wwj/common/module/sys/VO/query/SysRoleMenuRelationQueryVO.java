package com.wwj.common.module.sys.VO.query;

import com.wwj.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 后台角色菜单关系表 查询结果对象
 * </pre>
 *
 * @author Zhutaosong
 * @date 2021-06-01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysRoleMenuRelationQueryVO对象")
public class SysRoleMenuRelationQueryVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("角色ID")
    private Integer roleId;

    @ApiModelProperty("菜单ID")
    private Integer menuId;
}