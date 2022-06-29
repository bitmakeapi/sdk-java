package com.bitmake.open.api.domain.event;

import lombok.Data;

@Data
public class UserFuturesPositionItem {
    private String symbol;
    private String margin;
    private String quantity;
    private String avgPrice;
    private String liquidationPrice;
    private String realizedPnl;
    private String marginRate;
    private String openValue;
    private Long updateTime;
}
