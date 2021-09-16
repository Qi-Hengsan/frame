package com.wwj.common.module.sys.param;


import com.wwj.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 后台用户角色表 分页参数对象
 * </pre>
 *
 * @author Zhutaosong
 * @date 2021-06-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "后台用户角色表分页参数")
public class SysRolePageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色名称")
    private String queryRole;

    @ApiModelProperty("启用状态：0->禁用；1->启用")
    private Integer queryStatus;

}
