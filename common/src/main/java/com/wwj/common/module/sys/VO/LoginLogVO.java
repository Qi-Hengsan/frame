package com.wwj.common.module.sys.VO;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Classname LoginLogVO
 * @Description TODO
 * @Date 2021/7/9 16:42
 * @author by ztsong
 */
@Data
public class LoginLogVO implements Serializable {

    @ApiModelProperty("序号")
    @ExcelProperty("序号")
    private Long serNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("登录时间")
    @ExcelProperty("登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty("账号")
    @ExcelProperty("账号")
    private String username;

    @ApiModelProperty("浏览器类型")
    @ExcelProperty("浏览器类型")
    private String browser;

    @ApiModelProperty("操作系统")
    @ExcelProperty("操作系统")
    private String os;

    @ApiModelProperty("IP地址")
    @ExcelProperty("IP地址")
    private String ip;

}
