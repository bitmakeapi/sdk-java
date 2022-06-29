package com.bitmake.open.api.domain.order;

import lombok.Data;

/**
 * place spot order request
 *
 * @author bitmake
 */
@Data
public class PlaceOrderRequest {
    /**
     * spot or futures symbol
     */
    private String symbol;
    /**
     * custom order number (maximum length: 23 bytes)
     */
    private String clientOrderId;
    private String price;
    private String quantity;
    /**
     * enum order side:BUY or SELL
     */
    private String orderSide;
    /**
     * enum order type:LIMIT,MARKET, CONDITION_LIMIT, CONDITION_MARKET
     */
    private String orderType;
    /**
     * enum time in force:FOK, GTC, IOC
     */
    private String timeInForce;

    private String triggerMorePrice;
    private String triggerLessPrice;
    private String executeMorePrice;
    private String executeLessPrice;
}
