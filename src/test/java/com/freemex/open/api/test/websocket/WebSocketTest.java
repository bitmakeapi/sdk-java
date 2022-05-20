package com.freemex.open.api.test.websocket;

import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.domain.CandlestickInterval;
import com.freemex.open.api.service.websocket.ApiWebSocketClient;
import com.freemex.open.api.service.websocket.ApiWebSocketClientImpl;

import static com.freemex.open.api.client.ApiServiceGenerator.getSharedClient;

public class WebSocketTest {
    public static void main(String[] args) {

        final String SYMBOL = "BTC_USD";
        final String SYMBOL_FUTURE = "BTC-PERP";

        FreemexApiConfig apiConfig = new FreemexApiConfig();
        apiConfig.setQuoteWsBaseUrl("wss://ws.bytemars.co/t/v1");
        apiConfig.setUserWsBaseUrl("wss://ws.bytemars.co/t/v1");

        apiConfig.setSocketReConnect(false);
        apiConfig.setApiKey("aabIN8901nT1tpF1N7FTNftdDIxXPhSbQcJtqkjwDcNUuZIVUYhLCxGxtdD0WUoB");
        apiConfig.setSecretKey("BIhVyOm072LC6pWTePM4SWAvC0EN0wMMYE7KM6TYdAqwBpdMHmOcfZQKxzPmqOOq");

        ApiWebSocketClient client = new ApiWebSocketClientImpl(getSharedClient(), apiConfig);
        // depth
        client.onDepthEvent(SYMBOL, System.out::println);

        // kline
        // client.onCandlestickEvent("brokerbtcusdt123", CandlestickInterval.ONE_MINUTE, System.out::println);

        // client.onUserBalanceEvent("brokerbtcusdt123", System.out::println);

        System.out.println("Listener..........");
    }
}
