package com.wwj.common.module.sys.VO.query;

import com.wwj.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 后台用户和角色关系表 查询结果对象
 * </pre>
 *
 * @author Zhutaosong
 * @date 2021-06-01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysAdminRoleRelationQueryVO对象")
public class SysAdminRoleRelationQueryVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer adminId;

    private Integer roleId;
}