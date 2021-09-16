package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by ztsong
 * @Classname OrderStatusEnum
 * @Description 订单状态
 * @Date 2021/8/20 16:25
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    NOT_SUBMIT(1, "待提交"),
    NOT_REVIEW(2, "待审核"),
    REJECTED(3, "已驳回"),
    PASSED(4, "已通过"),
    CANCELLED(5, "已取消"),
    WITHDRAWN(6, "已撤回");

    private final Integer code;

    private final String name;

    /**
     * 根据name获取code
     * @param name
     * @return
     */
    public static Integer getValueByCode(String name){
        for(OrderStatusEnum platformFree:OrderStatusEnum.values()){
            if(name.equals(platformFree.getName())){
                return platformFree.getCode();
            }
        }
        return null;
    }
}
