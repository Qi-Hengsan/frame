package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by ztsong
 * @Classname ReviewOpinionNnum
 * @Description TODO
 * @Date 2021/8/24 13:37
 */
@Getter
@AllArgsConstructor
public enum ReviewOpinionEnum {

    REJECTED(0, "驳回"),
    PASSED(1, "通过");

    private final Integer code;

    private final String name;

}
