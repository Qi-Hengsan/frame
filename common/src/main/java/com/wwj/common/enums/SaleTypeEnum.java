package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by ztsong
 * @Classname SaleTypeEnum
 * @Description TODO
 * @Date 2021/8/25 14:18
 */
@Getter
@AllArgsConstructor
public enum SaleTypeEnum {

    QUOTA("Q","定额"),
    ROOM("R","房间");

    private String code;
    private String name;

}
