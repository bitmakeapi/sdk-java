package com.bitmake.open.api.service.order;

import com.bitmake.open.api.domain.order.*;

import java.util.List;

public interface OrderService {

    /**
     * place order
     *
     * @param request
     * @return
     */
    PlaceOrderResponse placeOrder(PlaceOrderRequest request);

    /**
     * cancel order
     *
     * @param clientOrderId custom order number (maximum length: 23 bytes)
     * @param orderId order id
     * @return
     */
    CancelOrderResponse cancelOrder(String clientOrderId, Long orderId);

    /**
     * batch cancel order
     *
     * @param request
     * @return
     */
    List<BatchCancelOrderResponse> batchCancelOrder(List<CancelOrderRequest> request);

    /**
     * Get all open orders
     *
     * @param symbol symbol name
     * @param orderSide enum order side:BUY or SELL
     * @param orderType enum order type:LIMIT,MARKET, CONDITION_LIMIT, CONDITION_MARKET
     * @return
     */
    List<OrderInfoResponse> getOpenOrders(String symbol, String orderSide, String orderType);


    /**
     * Get all open orders
     *
     * @param symbol symbol name
     * @param orderSide enum order side:BUY or SELL
     * @param orderType enum order type:LIMIT,MARKET, CONDITION_LIMIT, CONDITION_MARKET
     * @param fromId query start data id, default 0,null means default
     * @param limit limit number, default 100,null means default
     * @return
     */
    List<OrderInfoResponse> getOpenOrders(String symbol, String orderSide, String orderType, Long fromId, Long limit);

    /**
     * Get all history orders
     *
     * @param symbol symbol name
     * @param orderSide enum order side:BUY or SELL
     * @param orderType enum order type:LIMIT,MARKET, CONDITION_LIMIT, CONDITION_MARKE
     * @param fromIndex query start data id, default 0,null means default
     * @param limit limit number, default 100,null means default
     * @return
     */
    List<OrderInfoResponse> getHistoryOrders(String symbol, String orderSide, String orderType, Long fromIndex, Long limit);

    /**
     * Get all history orders
     *
     * @param symbol symbol name
     * @param orderSide enum order side:BUY or SELL
     * @param orderType enum order type:LIMIT,MARKET, CONDITION_LIMIT, CONDITION_MARKE
     * @return
     */
    List<OrderInfoResponse> getHistoryOrders(String symbol, String orderSide, String orderType);

    /**
     * Fetch order by clientOrderId、orderId
     *
     * @param clientOrderId custom order number, length must be less than 23 characters
     * @param orderId
     * @return
     */
    OrderInfoResponse getOrder(String clientOrderId, Long orderId);

    /**
     * Get trades
     *
     * @param symbol symbol name
     * @param orderSide enum order side:BUY or SELL
     * @param openClose OPEN or CLOSE
     * @param matchType NORMAL, ADL, LIQUIDATION, SETTLEMENT
     * @param fromId query start data id, default 0,null means default
     * @param limit limit number, default 100,null means default
     * @return
     */
    List<TradeInfoResponse> getTrades(String symbol, String orderSide, String openClose, String matchType, Long fromId, Long limit);

    /**
     * Get trades
     *
     * @param symbol symbol name
     * @param orderSide enum order side:BUY or SELL
     * @param openClose OPEN or CLOSE
     * @param matchType NORMAL, ADL, LIQUIDATION, SETTLEMENT
     * @return
     */
    List<TradeInfoResponse> getTrades(String symbol, String orderSide, String openClose, String matchType);
}
