package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @program: dmyl-server
 * @className: FileTypeEnum
 * @description: 文件类型
 * @author: 122439195@qq.com
 * @date: 2021-08-30 11:11
 **/
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    // 图片
    IMAGE(1, "图片"),
    // 附件
    ANNEX(2, "附件");

    private final Integer code;

    private final String name;


    /**
     * 根据name获取code
     *
     * @param name 名称
     * @return code
     */
    public static Integer getCodeByName(String name) {
        for (FileTypeEnum platformFree : FileTypeEnum.values()) {
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
        FileTypeEnum[] ecs = FileTypeEnum.class.getEnumConstants();
        for (FileTypeEnum ec : ecs) {
            if (Objects.equals(ec.getCode(), code)) {
                return ec.getName();
            }
        }
        return "";
    }
}
