package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname SysAdminByOrgVO
 * @Description TODO
 * @Date 2021/7/7 16:59
 * @author by ztsong
 */
@Data
public class SysAdminByOrgVO {
    @ApiModelProperty("员工id")
    private Integer userId;

    @ApiModelProperty("姓名")
    private String name;
}
