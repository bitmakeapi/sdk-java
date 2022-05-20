package com.freemex.open.api.domain.order;

import lombok.Data;

/**
 * place spot order response
 *
 * @author freemex
 */
@Data
public class PlaceOrderResponse {
    private String symbol;
    private Long orderId;
    private String clientOrderId;
    private Long createTime;
}
