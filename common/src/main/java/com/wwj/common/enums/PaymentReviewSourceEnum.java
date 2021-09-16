package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @program: dmyl-server
 * @className: PamentReviewSourceEnum
 * @description: 付款审核来源
 * @author: 122439195@qq.com
 * @date: 2021-08-26 15:41
 **/
@Getter
@AllArgsConstructor
public enum PaymentReviewSourceEnum {

    // 计调
    OP(1, "计调"),
    // 财务
    FIN(2, "财务");

    private final Integer code;

    private final String name;


    /**
     * 根据name获取code
     *
     * @param name 名称
     * @return code
     */
    public static Integer getCodeByName(String name) {
        for (PaymentReviewSourceEnum platformFree : PaymentReviewSourceEnum.values()) {
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
        PaymentReviewSourceEnum[] ecs = PaymentReviewSourceEnum.class.getEnumConstants();
        for (PaymentReviewSourceEnum ec : ecs) {
            if (Objects.equals(ec.getCode(), code)) {
                return ec.getName();
            }
        }
        return "";
    }
}
