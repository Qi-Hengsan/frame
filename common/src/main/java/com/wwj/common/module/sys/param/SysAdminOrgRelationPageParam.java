package com.wwj.common.module.sys.param;

import com.wwj.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 *  分页参数对象
 * </pre>
 *
 * @author ztsong
 * @date 2021-06-29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "分页参数")
public class SysAdminOrgRelationPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
