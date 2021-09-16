package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: dmyl-server
 * @className: PaymentInvoiceStatusEnum
 * @description: 发票情况
 * @author: 122439195@qq.com
 * @date: 2021-08-31 16:06
 **/
@Getter
@AllArgsConstructor
public enum PaymentInvoiceStatusEnum {

    // 未开发票
    UN_OPEN(1, "未开发票"),
    // 已开发票
    OPENED(2, "已开发票"),
    // 不开发票
    NO_OPEN(3, "不开发票");


    private final Integer code;

    private final String name;

    /**
     * 根据name获取code
     * @param name 名称
     * @return Integer
     */
    public static Integer getValueByCode(String name){
        for(PaymentInvoiceStatusEnum platformFree: PaymentInvoiceStatusEnum.values()){
            if(name.equals(platformFree.getName())){
                return platformFree.getCode();
            }
        }
        return null;
    }
}
