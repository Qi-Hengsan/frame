package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: dmyl-server
 * @className: PaymentTransferStatusEnum
 * @description: 付款申请审核流程
 * @author: 122439195@qq.com
 * @date: 2021-08-31 11:35
 **/
@Getter
@AllArgsConstructor
public enum PaymentTransferStatusEnum {

    // 代理商提申请
    AGENT_SUBMIT_SUCCESS(1, "提交申请", "已提交付款申请，待审核。"),
    // 财务确认收款
    FIN_CONFIRM_PAYMENT(2, "财务确认收款", "付款申请已审核确认收款"),
    // 财务审核驳回申请
    FIN_REJECT(3, "财务审核驳回申请", "付款申请已审核已驳回"),
    // 撤回申请
    RECALL_APP(4, "撤回申请", "已撤回付款申请"),
    // 取消申请
    CANCEL_APP(5, "取消申请", "已撤回付款申请");


    private final Integer code;

    private final String name;

    private final String detail;

    /**
     * 根据name获取code
     * @param name 名称
     * @return Integer
     */
    public static Integer getValueByCode(String name){
        for(PaymentTransferStatusEnum platformFree: PaymentTransferStatusEnum.values()){
            if(name.equals(platformFree.getName())){
                return platformFree.getCode();
            }
        }
        return null;
    }
}
