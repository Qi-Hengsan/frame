package com.wwj.common.module.question.entity;

import cn.craccd.mongoHelper.bean.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: frame
 * @className: UserTest
 * @description: 用户基本信息
 * @author: 122439195@qq.com
 * @date: 2021-09-23 10:15
 **/
@Data
@Document("user_test")
@ApiModel("用户基本信息")
public class UserTest extends BaseModel {

    @ApiModelProperty("唯一标识")
    private String uuid;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("年龄")
    private Integer age;
}
