package com.wwj.common.module.sys.VO;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname SysAdminUpdateInfoVO
 * @Description TODO
 * @Date 2021/6/30 10:58
 * @author by ztsong
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysAdminInfoVO对象")
public class SysAdminInfoVO implements Serializable {

    @ExcelIgnore
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @ExcelProperty("序号")
    private Long serNo;

    @ApiModelProperty("id")
    @ExcelIgnore
    private Integer id;

    @ApiModelProperty("用户名")
    @ExcelProperty("登录账号")
    private String username;

    @ApiModelProperty("姓名")
    @ExcelProperty("用户姓名")
    private String name;

    @ApiModelProperty("密码")
    @ExcelIgnore
    private String password;

    @ApiModelProperty("身份证号")
    @ExcelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("性别：0->男；1->女")
    @ExcelIgnore
    private Integer sex;

    @ApiModelProperty("性别：0->男；1->女")
    @ExcelProperty("性别")
    private String sexName;

    @ApiModelProperty("电话")
    @ExcelProperty("手机号")
    private String tel;

    @ApiModelProperty("启用状态（0->禁用,1->启用）")
    @ExcelIgnore
    private Integer status;

    @ApiModelProperty("启用状态（0->禁用,1->启用）")
    @ExcelProperty("启用")
    private String statusName;

    @ApiModelProperty("主部门")
    @ExcelIgnore
    private List<SysAdminListOrgInfoVO> mainDeptment;


    @ApiModelProperty("所属部门")
    @ExcelIgnore
    private List<SysAdminListOrgInfoVO> department;

    @ApiModelProperty("所属部门")
    @ExcelProperty("所属部门")
    private String mainDepName;

    @ApiModelProperty("所属角色")
    @ExcelIgnore
    private List<SysAdminRoleInfoVO>  role;

    @ApiModelProperty("所属角色")
    @ExcelProperty("所属角色")
    private String roleName;

}
