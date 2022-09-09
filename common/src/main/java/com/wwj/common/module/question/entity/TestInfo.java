package com.wwj.common.module.question.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: frame
 * @className: TestInfo
 * @description: 试题基本信息
 * @author: 122439195@qq.com
 * @date: 2021-09-23 10:15
 **/
@Data
@Document("TestInfo")
@ApiModel("试题基本信息")
public class TestInfo {

    @ApiModelProperty("唯一标识")
    private String uuid;

    @ApiModelProperty("题干")
    private String questionName;

    @ApiModelProperty("答案")
    private String answer;

    @ApiModelProperty("解析")
    private String answerAbs;
}
