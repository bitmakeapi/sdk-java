package com.bitmake.open.api.domain.account;

import lombok.Data;

@Data
public class MarginInfoResponse {
    private String symbol;
    private String totalCollateral;
    private String freeCollateral;
    private String maxLeverage;
    private String marginFraction;
    private String maintenanceMarginRequirement;
    private String shortMax;
    private String shortMaxNonBorrow;
    private String longMax;
    private String longMaxNonBorrow;
    private String baseAvailable;
    private String quoteAvailable;
}
