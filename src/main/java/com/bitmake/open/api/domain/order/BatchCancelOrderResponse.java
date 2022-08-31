package com.bitmake.open.api.domain.order;

import lombok.Data;

/**
 * cancel order response
 */
@Data
public class BatchCancelOrderResponse {
    private Long orderId;
    private String cancelStatus;
}
