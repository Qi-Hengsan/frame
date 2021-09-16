package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @program: dmyl-server
 * @className: ReceiptTypeEnum
 * @description: 单据类型
 * @author: 122439195@qq.com
 * @date: 2021-08-24 16:14
 **/
@Getter
@AllArgsConstructor
public enum ReceiptTypeEnum {

    // 常规
    COMMON(1, "常规"),
    // 留位
    RESERVED(2, "留位");

    private final Integer code;

    private final String name;

    /**
     * 根据name获取code
     *
     * @param name 名称
     * @return code
     */
    public static Integer getCodeByName(String name) {
        for (ReceiptTypeEnum platformFree : ReceiptTypeEnum.values()) {
            if (name.equals(platformFree.getName())) {
                return platformFree.getCode();
            }
        }
        return null;
    }

    /**
     * 根据coe获取name
     *
     * @param code 代号
     * @return name
     */
    public static String getNameByCode(Integer code) {
        if (code == null) {
            return "";
        }
        ReceiptTypeEnum[] ecs = ReceiptTypeEnum.class.getEnumConstants();
        for (ReceiptTypeEnum ec : ecs) {
            if (Objects.equals(ec.getCode(), code)) {
                return ec.getName();
            }
        }
        return "";
    }
}
