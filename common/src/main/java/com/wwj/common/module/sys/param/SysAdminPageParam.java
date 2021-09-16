package com.wwj.common.module.sys.param;

import com.wwj.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 后台用户表 分页参数对象
 * </pre>
 *
 * @author Zhutaosong
 * @date 2021-06-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "后台用户表分页参数")
public class SysAdminPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("姓名")
    private String queryName;

    @ApiModelProperty("角色ID")
    private Integer queryRoleId;

}
