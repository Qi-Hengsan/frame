package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by ztsong
 * @Classname OrderOperationStatusEnum
 * @Description TODO
 * @Date 2021/8/20 16:38
 */
@Getter
@AllArgsConstructor
public enum  OrderOperationStatusEnum {

    NORMAL(1, "正常"),
    MODIFY(2, "变更订单内容"),
    CANCEL(3, "取消订单");

    private final Integer code;

    private final String name;

    /**
     * 根据name获取code
     * @param name
     * @return
     */
    public static Integer getCodeByName(String name){
        for(OrderOperationStatusEnum platformFree:OrderOperationStatusEnum.values()){
            if(name.equals(platformFree.getName())){
                return platformFree.getCode();
            }
        }
        return null;
    }

    /**
     * 根据code获取name
     * @param code
     * @return
     */
    public static String getNameByCode(Integer code){
        for(OrderOperationStatusEnum platformFree:OrderOperationStatusEnum.values()){
            if(code.equals(platformFree.getCode())){
                return platformFree.getName();
            }
        }
        return null;
    }
}
