package com.bitmake.open.api.domain.order;

import lombok.Data;

/**
 * cancel order response
 */
@Data
public class CancelOrderResponse {
    private Long orderId;
    private String cancelStatus;
    private String orderStatus;
    private String executedQuantity;
    private String executedAmount;
}
