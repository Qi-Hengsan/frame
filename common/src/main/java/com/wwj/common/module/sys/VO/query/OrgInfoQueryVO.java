package com.wwj.common.module.sys.VO.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <pre>
 * 组织部门信息表 查询结果对象
 * </pre>
 *
 * @author Xiaoqing Liu
 * @date 2021-05-26
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "OrgInfoQueryVO对象")
public class OrgInfoQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("组织名称：公司或者部门名称")
    private String orgName;

    @ApiModelProperty("组织简称")
    private String orgShort;

    @ApiModelProperty("组织编号")
    private String orgNo;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("上级组织")
    private Integer parentId;

    @ApiModelProperty("说明")
    private String des;

    @ApiModelProperty("启用状态：1（true）是 0（false）否")
    private Boolean isEnable;

    @ApiModelProperty("更新人")
    private Integer updateUser;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人")
    private Integer createUser;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}