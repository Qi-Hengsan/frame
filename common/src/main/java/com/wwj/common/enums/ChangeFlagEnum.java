package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by ztsong
 * @Classname ChangeFlagEnum
 * @Description TODO
 * @Date 2021/9/1 11:10
 */
@Getter
@AllArgsConstructor
public enum ChangeFlagEnum {

    NOT_CHANGE(0, "未变更"),
    CHANGE(1, "变更");

    private final Integer code;

    private final String name;

}
