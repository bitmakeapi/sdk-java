package com.bitmake.open.api.domain.order;

import lombok.Data;

/**
 * place spot order response
 *
 * @author bitmake
 */
@Data
public class PlaceOrderResponse {
    private String symbol;
    private Long orderId;
    private String clientOrderId;
    private Long createTime;
}
