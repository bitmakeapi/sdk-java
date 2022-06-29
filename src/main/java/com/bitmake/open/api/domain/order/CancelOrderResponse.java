package com.bitmake.open.api.domain.order;

import lombok.Data;

/**
 * cancel order response
 */
@Data
public class CancelOrderResponse {
    private Long orderId;
    private Integer cancelStatus;
    private Integer orderStatus;
    private String executedQuantity;
    private String executedAmount;
}
