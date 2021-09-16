package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname OprationLogSelectVO
 * @Description TODO
 * @Date 2021/7/12 11:57
 * @author by ztsong
 */
@Data
public class OprationLogSelectVO implements Serializable {

    @ApiModelProperty("label")
    private String label;

    @ApiModelProperty("value")
    private String value;

}
