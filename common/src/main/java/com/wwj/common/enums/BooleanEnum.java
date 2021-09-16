package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @program: dmyl-server
 * @className: BillReviewChangePriceEnum
 * @description: 账单是否改价
 * @author: 122439195@qq.com
 * @date: 2021-08-23 16:14
 **/
@Getter
@AllArgsConstructor
public enum BooleanEnum {

    // 否
    NO(0, "否"),
    // 是
    YES(1, "是");

    private final Integer code;

    private final String name;

    /**
     * 根据name获取code
     *
     * @param name 名称
     * @return code
     */
    public static Integer getCodeByName(String name) {
        for (BooleanEnum platformFree : BooleanEnum.values()) {
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
        BooleanEnum[] ecs = BooleanEnum.class.getEnumConstants();
        for (BooleanEnum ec : ecs) {
            if (Objects.equals(ec.getCode(), code)) {
                return ec.getName();
            }
        }
        return "";
    }
}
