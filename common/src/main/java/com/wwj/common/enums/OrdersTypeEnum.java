package com.wwj.common.enums;

/**
 * @author wushilin
 * @version 1.0
 * @date 2021/08/23 16:23:01
 */
public enum OrdersTypeEnum {
    ORDINARY(1,"常规"),
    RESERVED(2,"留位")
    ;

    private Integer code;
    private String name;

    OrdersTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
