package com.wwj.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: frame
 * @className: Enabled
 * @description: 启用
 * @author: 122439195@qq.com
 * @date: 2022-04-15 14:57
 **/
@Getter
@AllArgsConstructor
public enum EnabledEnum {
    /**
     * 启用
     */
    ENABLED(1, "启用"),
    /**
     * 禁用
     */
    DISABLED(0, "禁用");

    @EnumValue
    private final Integer code;
    @JsonValue
    private final String desc;
}

