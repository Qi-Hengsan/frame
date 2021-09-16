package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by ztsong
 * @Classname OrderChangeStatusEnum
 * @Description 订单变更状态
 * @Date 2021/8/23 11:45
 */
@Getter
@AllArgsConstructor
public enum  OrderChangeStatusEnum {

    NOT_REVIEW(1, "待审核"),
    REJECTED(2, "已驳回"),
    WITHDRAWN(3, "已撤回");

    private final Integer code;

    private final String name;

    /**
     * 根据name获取code
     * @param name
     * @return
     */
    public static Integer getValueByCode(String name){
        for(OrderChangeStatusEnum platformFree:OrderChangeStatusEnum.values()){
            if(name.equals(platformFree.getName())){
                return platformFree.getCode();
            }
        }
        return null;
    }

}
