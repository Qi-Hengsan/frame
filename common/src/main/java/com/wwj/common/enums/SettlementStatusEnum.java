package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @program: dmyl-server
 * @className: SettlementStatusEnum
 * @description: 提交状态
 * @author: 122439195@qq.com
 * @date: 2021-08-30 13:42
 **/
@Getter
@AllArgsConstructor
public enum SettlementStatusEnum {

    // 未提交
    NOT_SUBMIT(1, "未提交"),
    // 已提交
    SUBMITTED(2, "已提交");


    private final Integer code;

    private final String name;

    /**
     * 根据name获取code
     *
     * @param name 名称
     * @return code
     */
    public static Integer getCodeByName(String name) {
        for (SettlementStatusEnum platformFree : SettlementStatusEnum.values()) {
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
        SettlementStatusEnum[] ecs = SettlementStatusEnum.class.getEnumConstants();
        for (SettlementStatusEnum ec : ecs) {
            if (Objects.equals(ec.getCode(), code)) {
                return ec.getName();
            }
        }
        return "";
    }
}
