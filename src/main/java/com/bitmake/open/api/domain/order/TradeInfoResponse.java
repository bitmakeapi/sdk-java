package com.bitmake.open.api.domain.order;

import lombok.Data;

@Data
public class TradeInfoResponse {
    private Long tradeId;
    private Long orderId;
    private Long matchTime;
    private String matchType;
    private String symbol;
    private String price;
    private String quantity;
    private String tradeFee;
    private String tradeFeeToken;
    private Boolean isMaker;
    private Boolean isBuyer;
    private Integer tradeIndex;
}
