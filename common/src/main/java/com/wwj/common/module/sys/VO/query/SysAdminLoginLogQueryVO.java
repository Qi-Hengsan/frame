package com.wwj.common.module.sys.VO.query;

import com.wwj.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 后台用户登录日志表 查询结果对象
 * </pre>
 *
 * @author Zhutaosong
 * @date 2021-06-01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysAdminLoginLogQueryVO对象")
public class SysAdminLoginLogQueryVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer adminId;


    private String ip;

    @ApiModelProperty("浏览器类型")
    private String browser;

    @ApiModelProperty("操作系统")
    private String os;
}