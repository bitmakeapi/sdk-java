package com.freemex.open.api.domain.order;

import lombok.Data;

/**
 * cancel order request
 */
@Data
public class CancelOrderRequest {
    /**
     * custom order number (maximum length: 23 bytes)
     */
    private String clientOrderId;
    private Long orderId;
}
