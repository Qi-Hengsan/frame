package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @program: dmyl-server
 * @className: BillFileTypeEnum
 * @description: 账单文件区分
 * @author: 122439195@qq.com
 * @date: 2021-08-30 11:11
 **/
@Getter
@AllArgsConstructor
public enum BillFileTypeEnum {

    // 改价
    CHANGE_PRICE(1, "账单改价"),
    // 取消操作
    CANCEL(2, "账单取消");

    private final Integer code;

    private final String name;


    /**
     * 根据name获取code
     *
     * @param name 名称
     * @return code
     */
    public static Integer getCodeByName(String name) {
        for (BillFileTypeEnum platformFree : BillFileTypeEnum.values()) {
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
        BillFileTypeEnum[] ecs = BillFileTypeEnum.class.getEnumConstants();
        for (BillFileTypeEnum ec : ecs) {
            if (Objects.equals(ec.getCode(), code)) {
                return ec.getName();
            }
        }
        return "";
    }
}
