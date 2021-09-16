package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName SysAdminVO
 * @Description
 * @Author 谭雨
 * @Date 2021/6/17 10:27
 * @Version: 1.0
 **/
@Data
@Accessors(chain = true)
@ApiModel(value = "OrgInfoVO对象")
public class SysAdminVO implements Serializable {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;


}
