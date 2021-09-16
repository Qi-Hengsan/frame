package com.wwj.common.enums;

import java.util.Objects;

/**
 * 校验新增用户
 * liuxiaoqing
 * 2021-7-30
 */
public enum SaveEnum {

    SAVE_SUCCESS(1, "成功"),
    SAVE_FALSE(2, "数据异常，保存失败"),
    USERNAME_REPEAT(3,"该账号已存在"),
    CARD_REPEAT(4,"该身份证已存在");

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    SaveEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(Integer id) {
        if (id == null) {
            return "";
        }

        SaveEnum[] ecs = SaveEnum.class.getEnumConstants();
        for (SaveEnum ec : ecs) {
            if (Objects.equals(ec.getId(), id)) {
                return ec.getName();
            }
        }
        return "";
    }
}
