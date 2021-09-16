package com.wwj.common.module.sys.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by ztsong
 * @Classname OrderStatisticsVO
 * @Description TODO
 * @Date 2021/8/23 17:03
 */
@Data
public class OrderStatisticsVO {

    @ApiModelProperty("待审核订单")
    private Integer notReview;

    @ApiModelProperty("待审核申请")
    private Integer notReviewApply;

    @ApiModelProperty("今日人数")
    private Integer todayPeople;

    @ApiModelProperty("今日订单")
    private Integer todayOrder;

}
