package com.wwj.common.module.sys.VO.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Classname AllOrgInfoQueryVO
 * @Description TODO
 * @Date 2021/7/9 12:53
 * @author by ztsong
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "OrgInfoQueryVO对象")
public class AllOrgInfoQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("组织名称：公司或者部门名称")
    private String name;

    @ApiModelProperty("组织简称")
    private String orgShort;

    @ApiModelProperty("组织编号")
    private String orgNo;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("上级组织")
    private String parentId;

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
