package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @program: dmyl-server
 * @className: BillStatusEnum
 * @description: 账单状态
 * @author: 122439195@qq.com
 * @date: 2021-08-23 14:54
 **/
@Getter
@AllArgsConstructor
public enum BillStatusEnum {

    // 计调提交结算单，财务未审核
    FIN_CHECK_PENDING(1, "待审核"),
    // 财务审核驳回
    FIN_REJECT(2, "已驳回"),
    // 财务审核通过，未关联付款申请
    AGENT_TO_SETTLED(3, "待结算"),
    // 结算单关联的付款申请有“待确认”、“已驳回”、“已撤回”状态
    TO_CONFIRMED(4, "结算中"),
    // 结算单关联的付款申请均“已确认”，但结算金额未冲抵完全
    PARTIAL_SETTLEMENT(5, "部分结算"),
    // 计调取消结算，取消结算单与订单的关联，订单恢复“未提交”状态
    OP_CANCEL(6, "已取消"),
    // 计调撤回结算单
    OP_RECALL(7, "已撤回"),
    // 确认付款金额冲抵结算金额完全，结算单状态更新为已结算
    SETTLEMENT_COMPLETE(8, "已结算");

    private final Integer code;

    private final String name;

    /**
     * 根据name获取code
     *
     * @param name 名称
     * @return code
     */
    public static Integer getCodeByName(String name) {
        for (BillStatusEnum platformFree : BillStatusEnum.values()) {
            if (name.equals(platformFree.getName())) {
                return platformFree.getCode();
            }
        }
        return null;
    }

    /**
     * 根据coe获取name
     *
     * @param code 代号
     * @return name
     */
    public static String getNameByCode(Integer code) {
        if (code == null) {
            return "";
        }
        BillStatusEnum[] ecs = BillStatusEnum.class.getEnumConstants();
        for (BillStatusEnum ec : ecs) {
            if (Objects.equals(ec.getCode(), code)) {
                return ec.getName();
            }
        }
        return "";
    }
}
