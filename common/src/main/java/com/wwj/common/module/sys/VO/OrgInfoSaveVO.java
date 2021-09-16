package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName OrgInfoVO
 * @Description
 * @Author liuxiaoqing
 * @Date 2021/6/30 15:08
 * @Version: 1.0
 **/
@Data
@Accessors(chain = true)
@ApiModel(value = "OrgInfoSaveVO对象")
public class OrgInfoSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("组织名称：公司或者部门名称")
    private String orgName;

    @ApiModelProperty("组织简称")
    private String orgShort;

    @ApiModelProperty("组织编号")
    private String orgNo;

    @ApiModelProperty("上级组织")
    private Integer parentId;

    @ApiModelProperty("说明")
    private String des;

    @ApiModelProperty("启用状态：1（true）是 0（false）否")
    private Boolean isEnable;


}
