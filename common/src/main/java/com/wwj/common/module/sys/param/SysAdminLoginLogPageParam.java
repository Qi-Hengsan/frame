package com.wwj.common.module.sys.param;

import com.wwj.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 后台用户登录日志表 分页参数对象
 * </pre>
 *
 * @author Zhutaosong
 * @date 2021-06-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "后台用户登录日志表分页参数")
public class SysAdminLoginLogPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账号")
    private String userName;

    @ApiModelProperty("IP地址")
    private String ip;


}
