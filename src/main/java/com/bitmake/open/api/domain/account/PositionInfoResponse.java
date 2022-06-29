package com.bitmake.open.api.domain.account;

import lombok.Data;

@Data
public class PositionInfoResponse {
    private String symbol;
    private String margin;
    private String quantity;
    private String avgPrice;
    private String realizedPnl;
    private String marginRate;
    private String openValue;
    private Long updateTime;
}
