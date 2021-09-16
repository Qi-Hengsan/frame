package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by ztsong
 * @Classname SexEnum
 * @Description TODO
 * @Date 2021/8/26 18:10
 */
@Getter
@AllArgsConstructor
public enum SexEnum {

    MALE(0, "男"),
    FEMALE(1, "女");

    private final Integer code;

    private final String name;

    /**
     * 根据name获取code
     * @param name
     * @return
     */
    public static Integer getValueByCode(String name){
        for(OrderTransferStatusEnum platformFree:OrderTransferStatusEnum.values()){
            if(name.equals(platformFree.getName())){
                return platformFree.getCode();
            }
        }
        return null;
    }

}
