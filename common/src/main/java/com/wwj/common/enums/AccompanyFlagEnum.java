package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by ztsong
 * @Classname AccompanyFlagEnum
 * @Description 陪同房标记
 * @Date 2021/8/25 16:35
 */
@Getter
@AllArgsConstructor
public enum AccompanyFlagEnum {

    NO(0, "不是陪同房"),
    YES(1, "是陪同房");

    private final Integer code;

    private final String name;
}
