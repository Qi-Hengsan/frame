package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname SysMenuTreeVO
 * @Description TODO
 * @Date 2021/7/8 14:51
 * @author by ztsong
 */
@Data
public class SysMenuTreeVO implements Serializable {

    @ApiModelProperty("菜单ID")
    private Integer id;

    @ApiModelProperty("父级ID")
    private Integer parentId;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("菜单级数")
    private Integer level;

    @ApiModelProperty("菜单排序")
    private Integer sort;

    @ApiModelProperty("是否显示权限")
    private boolean showPermission;

    @ApiModelProperty("页面按钮操作权限，0->无权限，1->有权限")
    private Integer edit;

    @ApiModelProperty("子菜单")
    private List<SysMenuTreeVO> childMenu;

}
