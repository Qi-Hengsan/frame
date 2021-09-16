package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by ztsong
 * @Classname IdTypeEnum
 * @Description TODO
 * @Date 2021/8/26 17:46
 */
@Getter
@AllArgsConstructor
public enum IdTypeEnum {

    IDENTITY_CARD(1, "身份证（户口簿、出生证明）"),
    PASSPORT(2, "护照"),
    HK_MO_PASS(3, "港澳居民来往内地通行证"),
    TWN_PASS(4, "台湾居民来往大陆通行证"),
    FOREIGNER_IDENTITY_CARD(5, "外国人永久居留身份证"),
    HK_MO_TWN_IDENTITY_CARD(6, "港澳台居民居住证");

    private final Integer code;

    private final String name;

}
