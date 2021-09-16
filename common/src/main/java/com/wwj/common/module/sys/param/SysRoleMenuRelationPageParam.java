package com.wwj.common.module.sys.param;

import com.wwj.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 后台角色菜单关系表 分页参数对象
 * </pre>
 *
 * @author Zhutaosong
 * @date 2021-06-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "后台角色菜单关系表分页参数")
public class SysRoleMenuRelationPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
