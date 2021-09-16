package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by ztsong
 * @Classname TouristOrderEnum
 * @Description 是否支持游客下单
 * @Date 2021/8/25 16:39
 */
@Getter
@AllArgsConstructor
public enum TouristOrderEnum {

    NO(0, "不支持游客下单"),
    YES(1, "支持游客下单");

    private final Integer code;

    private final String name;

}
