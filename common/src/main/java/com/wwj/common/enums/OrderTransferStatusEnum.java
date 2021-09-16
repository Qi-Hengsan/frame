package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by ztsong
 * @Classname OrderTransferStatusEnum
 * @Description 订单流转状态
 * @Date 2021/8/23 14:30
 */
@Getter
@AllArgsConstructor
public enum  OrderTransferStatusEnum {

    FIRST_SAVE(1, "代理商首次保存", "创建订单，待提交"),
    SUBMIT_SUCCESS(2, "代理商提交成功", "订单已提交，待审核"),
    REVIEW_PASSED(3, "平台审核通过", "订单已审核"),
    REVIEW_REJECTED(4, "平台审核驳回", "订单已审核"),
    SUBMIT_CHANGE(5, "提交变更", "变更申请已提交，待审核"),
    CHANGE_REVIEW_PASSED(6, "变更审核通过", "变更申请已审核"),
    CHANGE_REVIEW_REJECTED(6, "变更审核驳回", "变更申请已审核"),
    REVOKE_ORDER(6, "代理商撤回订单", "您已撤回订单"),
    REVOKE_CHANGE(6, "代理商撤回变更", "您已撤回变更申请"),
    PLATFORM_CANCEL(6, "平台取消", "订单已取消");

    private final Integer code;

    private final String name;

    private final String detail;

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
