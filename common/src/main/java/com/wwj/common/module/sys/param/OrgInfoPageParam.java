package com.wwj.common.module.sys.param;

import com.wwj.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 组织部门信息表 分页参数对象
 * </pre>
 *
 * @author Xiaoqing Liu
 * @date 2021-05-26
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "组织部门信息表分页参数")
public class OrgInfoPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
