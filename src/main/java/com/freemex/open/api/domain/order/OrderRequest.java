package com.freemex.open.api.domain.order;

import lombok.Data;

@Data
public class OrderRequest {
    private String clientOrderId;
    private Long orderId;
}
