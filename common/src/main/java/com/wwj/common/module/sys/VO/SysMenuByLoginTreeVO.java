package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname SysMenuByLoginTreeVO
 * @Description TODO
 * @Date 2021/7/12 12:53
 * @author by ztsong
 */
@Data
public class SysMenuByLoginTreeVO implements Serializable {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("菜单等级")
    private int tabIndex;

    @ApiModelProperty("页面按钮操作权限，0->无权限，1->有权限")
    private Integer edit;

    @ApiModelProperty("子菜单")
    private List<SysMenuByLoginTreeVO> children;

}
