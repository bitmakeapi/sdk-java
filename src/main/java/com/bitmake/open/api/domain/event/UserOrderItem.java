package com.bitmake.open.api.domain.event;

import lombok.Data;

@Data
public class UserOrderItem {

    private String clientOrderId;
    private Long orderId;
    private String symbol;
    private String orderSide;
    private String orderType;
    private String timeInForce;
    private String orderStatus;
    private String price;
    private String quantity;
    private String amount;
    private String executedQuantity;
    private String executedAmount;
    private Long createTime;
    private Long updateTime;

}
