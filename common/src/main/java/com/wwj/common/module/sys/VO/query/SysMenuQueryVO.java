package com.wwj.common.module.sys.VO.query;

import com.wwj.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 后台菜单表 查询结果对象
 * </pre>
 *
 * @author Zhutaosong
 * @date 2021-06-01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysMenuQueryVO对象")
public class SysMenuQueryVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("父级ID")
    private Integer parentId;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("菜单级数")
    private Integer level;

    @ApiModelProperty("菜单排序")
    private Integer sort;

    @ApiModelProperty("前端名称")
    private String name;

    @ApiModelProperty("前端图标")
    private String icon;

    @ApiModelProperty("前端隐藏")
    private Integer hidden;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("类型")
    private Integer menu_type;

    @ApiModelProperty("认证")
    private String menu_auth;
}