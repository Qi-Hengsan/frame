package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <pre>
 *  查询结果对象
 * </pre>
 *
 * @author ztsong
 * @date 2021-06-29
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysAdminOrgRelationQueryVO对象")
public class SysAdminOrgRelationQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("用户ID")
    private Integer sysAdminId;

    @ApiModelProperty("部门ID")
    private Integer orgInfoId;

    @ApiModelProperty("部门顺序")
    private Integer sort;
}