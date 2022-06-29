package com.bitmake.open.api.service.order.impl;

import com.bitmake.open.api.constant.APIConstants;
import com.bitmake.open.api.domain.order.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface OrderServiceRetrofit {
    /**
     * place order
     *
     * @param request
     * @return
     */
    @POST("/f/v1/order")
    @Headers(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<PlaceOrderResponse> placeOrder(@Body PlaceOrderRequest request);

    /**
     * cancel order
     *
     * @param clientOrderId
     * @param orderId
     * @return
     */
    @DELETE("/f/v1/order")
    @Headers(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<CancelOrderResponse> cancelOrder(@Query("clientOrderId") String clientOrderId, @Query("orderId") Long orderId);

    /**
     * batch cancel order
     *
     * @param request
     * @return
     */
    @POST("/f/v1/order/batch_cancel")
    @Headers(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<List<CancelOrderResponse>> batchCancelOrder(@Body List<CancelOrderRequest> request);

    /**
     * get open orders
     *
     * @param symbol
     * @param orderSide
     * @param orderType
     * @param fromId
     * @param limit
     * @return
     */
    @GET("/f/v1/openOrders")
    @Headers(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<List<OrderInfoResponse>> getOpenOrders(@Query("symbol") String symbol, @Query("orderSide") String orderSide, @Query("orderType") String orderType
            , @Query("fromId") Long fromId, @Query("limit") Long limit);

    /**
     * get history orders
     *
     * @param symbol
     * @param orderSide
     * @param orderType
     * @param fromId
     * @param limit
     * @return
     */
    @GET("/f/v1/historyOrders")
    @Headers(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<List<OrderInfoResponse>> getHistoryOrders(@Query("symbol") String symbol, @Query("orderSide") String orderSide, @Query("orderType") String orderType
            , @Query("fromId") Long fromId, @Query("limit") Long limit);

    /**
     * get order
     *
     * @param clientOrderId
     * @param orderId
     * @return
     */
    @GET("/f/v1/order")
    @Headers(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<OrderInfoResponse> getOrder(@Query("clientOrderId") String clientOrderId, @Query("orderId") Long orderId);

    /**
     * get trades
     *
     * @param symbol
     * @param orderSide
     * @param openClose
     * @param matchType
     * @param fromId
     * @param limit
     * @return
     */
    @GET("/f/v1/trades")
    @Headers(APIConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    Call<List<TradeInfoResponse>> getTrades(@Query("symbol") String symbol, @Query("orderSide") String orderSide, @Query("openClose") String openClose
            , @Query("matchType") String matchType, @Query("fromId") Long fromId, @Query("limit") Long limit);
}
