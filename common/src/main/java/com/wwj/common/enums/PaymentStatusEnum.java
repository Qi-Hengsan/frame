package com.wwj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @program: dmyl-server
 * @className: PaymentStatusEnum
 * @description: 付款申请状态
 * @author: 122439195@qq.com
 * @date: 2021-08-25 11:17
 **/
@Getter
@AllArgsConstructor
public enum PaymentStatusEnum {

    // 代理商提交付款申请（关联结算单），财务未审核
    FIN_TO_AUDIT(1, "待确认"),
    // 财务审核驳回
    REJECTED(2, "已驳回"),
    // 代理商取消付款申请，解除与结算单关联
    AGENT_CANCEL(3, "已取消"),
    // 代理商撤回结算申请
    AGENT_RECALL(4, "已撤回"),
    // 财务审核确认冲抵结算
    PASSED(5, "已确认");

    private final Integer code;

    private final String name;

    /**
     * 根据name获取code
     *
     * @param name 名称
     * @return code
     */
    public static Integer getCodeByName(String name) {
        for (PaymentStatusEnum platformFree : PaymentStatusEnum.values()) {
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
        PaymentStatusEnum[] ecs = PaymentStatusEnum.class.getEnumConstants();
        for (PaymentStatusEnum ec : ecs) {
            if (Objects.equals(ec.getCode(), code)) {
                return ec.getName();
            }
        }
        return "";
    }
}
