package com.wwj.common.module.teacher.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wwj.common.enums.EnabledEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author wushilin
 * @version 1.0
 * @date 2021/08/18 16:45:14
 */
@ApiModel(value="代理商")
@Data
public class TeacherVO {
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 教师名称
     */
    @ApiModelProperty(value="教师名称")
    @NotBlank(message = "教师名称不能为空")
    private String name;

    /**
     * 账号
     */
    @ApiModelProperty(value="账号")
    @NotBlank(message = "账号不能我空")
    private String username;

    /**
     * 联系方式
     */
    @ApiModelProperty(value="联系方式")
    @NotBlank(message = "联系方式不能为空")
    private String phone;

    /**
     * 评分
     */
    @ApiModelProperty(value="评分")
    private Integer score;

    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String mail;

    /**
     * 状态：0->禁用，1->启用
     */
    @ApiModelProperty(value="状态：0->禁用，1->启用")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人")
    private String createUser;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value="更新人")
    private String updateUser;

    @ApiModelProperty(value="是否启用")
    private EnabledEnum enabled;
}
