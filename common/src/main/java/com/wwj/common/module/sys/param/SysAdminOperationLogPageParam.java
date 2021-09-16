package com.wwj.common.module.sys.param;

import com.wwj.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 *  分页参数对象
 * </pre>
 *
 * @author ztsong
 * @date 2021-07-07
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "分页参数")
public class SysAdminOperationLogPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("操作人员账号/IP")
    private String info;

    @ApiModelProperty("操作板块")
    private String model;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    @ApiModelProperty("开始时间")
//    private LocalDate startDate;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
//    @ApiModelProperty("结束时间")
//    private LocalDate endDate;

}
