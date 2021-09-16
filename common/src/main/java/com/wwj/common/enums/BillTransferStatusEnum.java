package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: dmyl-server
 * @className: BillTransferStatusEnum
 * @description: 账单审核流程
 * @author: 122439195@qq.com
 * @date: 2021-08-27 16:01
 **/
@Getter
@AllArgsConstructor
public enum BillTransferStatusEnum {

    // 计调结算提交
    OP_SUBMIT_SUCCESS(1, "计调结算提交", "提交结算单，待审核。"),
    // 财务审核通过
    FIN_PASS(2, "财务审核通过", "审核通过"),
    // 财务审核驳回
    FIN_REJECT(3, "财务审核驳回", "审核驳回"),
    // 计调撤回账单
    OP_RECALL(4, "撤回结算单", "撤回结算单。"),
    // 计调取消账单
    OP_CANCEL(5, "取消结算单", "取消结算单。"),
    // 部分结算
    PARTIAL_SETTLEMENT(6, "财务确认付款申请冲抵结算单", "确认代理商付款"),
    // 结算完成
    SETTLEMENT_COMPLETE(7, "财务确认付款申请冲抵结算单完全", "结算完成。");


    private final Integer code;

    private final String name;

    private final String detail;

    /**
     * 根据name获取code
     * @param name 名称
     * @return Integer
     */
    public static Integer getValueByCode(String name){
        for(BillTransferStatusEnum platformFree:BillTransferStatusEnum.values()){
            if(name.equals(platformFree.getName())){
                return platformFree.getCode();
            }
        }
        return null;
    }
}
