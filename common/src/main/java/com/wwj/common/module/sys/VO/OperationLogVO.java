package com.wwj.common.module.sys.VO;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Classname OperationLogVO
 * @Description TODO
 * @Date 2021/7/9 16:57
 * @author by ztsong
 */
@Data
public class OperationLogVO implements Serializable {

    @ApiModelProperty("序号")
    @ExcelProperty("序号")
    private Long serNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("日志时间")
    @ExcelProperty("日志时间")
    private LocalDateTime logTime;

    @ApiModelProperty("操作板块")
    @ExcelProperty("操作板块")
    private String model;

    @ApiModelProperty("账号")
    @ExcelProperty("账号")
    private String username;

    @ApiModelProperty("IP地址")
    @ExcelProperty("IP地址")
    private String ip;

    @ApiModelProperty("内容")
    @ExcelProperty("内容")
    private String content;

}
