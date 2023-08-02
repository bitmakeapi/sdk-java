package com.bitmake.open.api.service.order.impl;

import com.bitmake.open.api.client.ApiServiceGenerator;
import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.constant.APIConstants;
import com.bitmake.open.api.domain.order.*;
import com.bitmake.open.api.service.order.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderServiceRetrofit orderServiceRetrofit;

    public OrderServiceImpl(BitmakeApiConfig config) {
        orderServiceRetrofit = ApiServiceGenerator.createService(OrderServiceRetrofit.class, config);
    }

    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {
        return ApiServiceGenerator.executeSync(orderServiceRetrofit.placeOrder(request));
    }

    @Override
    public CancelOrderResponse cancelOrder(String clientOrderId, Long orderId) {
        return ApiServiceGenerator.executeSync(orderServiceRetrofit.cancelOrder(clientOrderId, orderId));
    }

    @Override
    public List<BatchCancelOrderResponse> batchCancelOrder(List<CancelOrderRequest> request) {
        return ApiServiceGenerator.executeSync(orderServiceRetrofit.batchCancelOrder(request));
    }

    @Override
    public List<OrderInfoResponse> getOpenOrders(String symbol, String orderSide, String orderType, Long fromId, Long limit) {
        return ApiServiceGenerator.executeSync(orderServiceRetrofit.getOpenOrders(symbol, orderSide, orderType, fromId, limit));
    }

    @Override
    public List<OrderInfoResponse> getOpenOrders(String symbol, String orderSide, String orderType) {
        return ApiServiceGenerator.executeSync(orderServiceRetrofit.getOpenOrders(symbol, orderSide, orderType, APIConstants.DEFAULT_FROM_ID, APIConstants.DEFAULT_LIMIT));
    }

    @Override
    public List<OrderInfoResponse> getHistoryOrders(String symbol, String orderSide, String orderType, Long fromIndex, Long limit) {
        return ApiServiceGenerator.executeSync(orderServiceRetrofit.getHistoryOrders(symbol, orderSide, orderType, fromIndex, limit));
    }

    @Override
    public List<OrderInfoResponse> getHistoryOrders(String symbol, String orderSide, String orderType) {
        return ApiServiceGenerator.executeSync(orderServiceRetrofit.getHistoryOrders(symbol, orderSide, orderType, APIConstants.DEFAULT_FROM_ID, APIConstants.DEFAULT_LIMIT));
    }

    @Override
    public OrderInfoResponse getOrder(String clientOrderId, Long orderId) {
        return ApiServiceGenerator.executeSync(orderServiceRetrofit.getOrder(clientOrderId, orderId));
    }

    @Override
    public List<TradeInfoResponse> getTrades(String symbol, String orderSide, String openClose, String matchType, Long fromId, Long limit) {
        return ApiServiceGenerator.executeSync(orderServiceRetrofit.getTrades(symbol, orderSide, openClose, matchType, fromId, limit));
    }

    @Override
    public List<TradeInfoResponse> getTrades(String symbol, String orderSide, String openClose, String matchType) {
        return ApiServiceGenerator.executeSync(orderServiceRetrofit.getTrades(symbol, orderSide, openClose, matchType, APIConstants.DEFAULT_FROM_ID, APIConstants.DEFAULT_LIMIT));
    }
}
