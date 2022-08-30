package com.bitmake.open.api.domain.order;

import lombok.Data;

@Data
public class OrderInfoResponse {
    private String clientOrderId;
    private Long orderId;
    private String symbol;
    private String orderSide;
    private String orderType;
    private String orderStatus;
    private String timeInForce;
    private String price;
    private String quantity;
    private String amount;
    private String executedQuantity;
    private String executedAmount;
    private String triggerMorePrice;
    private String triggerLessPrice;
    private String executedMorePrice;
    private String executeLessPrice;
    private Integer historyOrderIndex;
    private Long createTime;
    private Long updateTime;
}
