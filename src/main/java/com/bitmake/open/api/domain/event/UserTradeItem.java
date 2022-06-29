package com.bitmake.open.api.domain.event;

import lombok.Data;

@Data
public class UserTradeItem {
    private Long tradeId;
    private Long orderId;
    private Long matchTime;
    private String matchType;
    private String symbol;
    private String price;
    private String quantity;
    private String tradeFee;
    private String tradeFeeToken;
    private Boolean isBuyer;
    private Boolean isMaker;
}
