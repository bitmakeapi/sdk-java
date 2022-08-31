package com.bitmake.open.api.test.order;

import com.bitmake.open.api.domain.order.*;
import com.bitmake.open.api.enums.EnumMatchType;
import com.bitmake.open.api.enums.EnumOrderSide;
import com.bitmake.open.api.enums.EnumOrderType;
import com.bitmake.open.api.enums.EnumTimeInForce;
import com.bitmake.open.api.service.order.OrderService;
import com.bitmake.open.api.domain.order.*;
import com.bitmake.open.api.enums.*;
import com.bitmake.open.api.service.order.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderTest extends OrderBaseTest {
    private OrderService orderService;

    private static final String CLIENT_ORDER_ID = "open-api-test-006";

    @Before
    public void before() {
        this.config = this.config();
        this.orderService = new OrderServiceImpl(this.config);
    }

    /**
     * place order
     */
    @Test
    public void placeOrder() {
        PlaceOrderRequest request = new PlaceOrderRequest();
        request.setSymbol(SYMBOL_FUTURE);
        request.setClientOrderId(CLIENT_ORDER_ID);
        request.setPrice("20256.5");
        request.setQuantity("0.8");
        request.setOrderSide(EnumOrderSide.SELL.name());
        request.setOrderType(EnumOrderType.LIMIT.name());
        request.setTimeInForce(EnumTimeInForce.GTC.name());
        PlaceOrderResponse response = this.orderService.placeOrder(request);
        toResultString("result", response);
    }

    @Test
    public void cancelOrder() {
        CancelOrderResponse response = this.orderService.cancelOrder(CLIENT_ORDER_ID, 0L);
        toResultString("result", response);
    }

    @Test
    public void batchCancelOrder() {

        List<CancelOrderRequest> requests = new ArrayList<>();

        CancelOrderRequest request = new CancelOrderRequest();
        request.setClientOrderId(CLIENT_ORDER_ID);
        request.setOrderId(0L);

        CancelOrderRequest request1 = new CancelOrderRequest();
        request1.setClientOrderId("open-api-test-004");
        request1.setOrderId(0L);

        CancelOrderRequest request2 = new CancelOrderRequest();
        request2.setClientOrderId("open-api-test-003");
        request2.setOrderId(0L);

        requests.add(request);
        requests.add(request1);
        requests.add(request2);

        List<BatchCancelOrderResponse> response = this.orderService.batchCancelOrder(requests);
        toResultString("result", response);
    }

    @Test
    public void getOpenOrders() {
        List<OrderInfoResponse> response = this.orderService.getOpenOrders(SYMBOL, EnumOrderSide.BUY.name(), EnumOrderType.LIMIT.name(), null, null);
        toResultString("result", response);
    }

    @Test
    public void getHistoryOrders() {
        List<OrderInfoResponse> response = this.orderService.getHistoryOrders(SYMBOL, EnumOrderSide.BUY.name(), EnumOrderType.LIMIT.name(), 0L, 100L);
        toResultString("result", response);
    }

    @Test
    public void getOrder() {
        OrderInfoResponse response = this.orderService.getOrder(CLIENT_ORDER_ID, 0L);
        toResultString("result", response);
    }

    @Test
    public void getTrades() {
        List<TradeInfoResponse> response = this.orderService.getTrades(SYMBOL, EnumOrderSide.BUY.name(), null, EnumMatchType.NORMAL.name(), 0L, 100L);
        toResultString("result", response);
    }
}
