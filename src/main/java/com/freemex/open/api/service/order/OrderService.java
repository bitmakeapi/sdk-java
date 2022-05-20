package com.freemex.open.api.service.order;

import com.freemex.open.api.domain.order.*;

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
     * @param clientOrderId
     * @param orderId
     * @return
     */
    CancelOrderResponse cancelOrder(String clientOrderId, Long orderId);

    /**
     * batch cancel order
     *
     * @param request
     * @return
     */
    List<CancelOrderResponse> batchCancelOrder(List<CancelOrderRequest> request);

    /**
     * Get all open orders
     *
     * @param symbol
     * @param orderSide
     * @param orderType
     * @param fromId
     * @param limit
     * @return
     */
    List<OrderInfoResponse> getOpenOrders(String symbol, String orderSide, String orderType, Long fromId, Long limit);

    /**
     * Get all history orders
     *
     * @param symbol
     * @param orderSide
     * @param orderType
     * @param fromId
     * @param limit
     * @return
     */
    List<OrderInfoResponse> getHistoryOrders(String symbol, String orderSide, String orderType, Long fromId, Long limit);

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
     * @param symbol
     * @param orderSide
     * @param openClose OPEN or CLOSE
     * @param matchType NORMAL, ADL, LIQUIDATION, SETTLEMENT
     * @param fromId
     * @param limit
     * @return
     */
    List<TradeInfoResponse> getTrades(String symbol, String orderSide, String openClose, String matchType, Long fromId, Long limit);
}
