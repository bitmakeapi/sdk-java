package com.bitmake.open.api.test.websocket;

import com.bitmake.open.api.client.ApiServiceGenerator;
import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.domain.CandlestickInterval;
import com.bitmake.open.api.domain.event.ChannelRequest;
import com.bitmake.open.api.domain.event.EventTopic;
import com.bitmake.open.api.domain.event.EventType;
import com.bitmake.open.api.service.websocket.ApiWebSocketClient;
import com.bitmake.open.api.service.websocket.ApiWebSocketClientImpl;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WebSocketTest {
    public static void main(String[] args) {

        final String SYMBOL = "BTC_USD";
        final String SYMBOL_FUTURE = "BTC-PERP";

        BitmakeApiConfig apiConfig = new BitmakeApiConfig();

        apiConfig.setSocketReConnect(false);
        apiConfig.setApiKey("apiKey");
        apiConfig.setSecretKey("SecretKey");

        ApiWebSocketClient client = new ApiWebSocketClientImpl(ApiServiceGenerator.getSharedClient(), apiConfig);

        // depth
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", SYMBOL);
        params.put("dumpScale", "1");
        params.put("depthLevel", "1");// depth level:1-five 2-ten 3-twenty 4-fifty 5-one hundred

        ChannelRequest requestDepth = new ChannelRequest();
        requestDepth.setTopic(EventTopic.DIFF_MERGED_DEPTH.getTopic());
        requestDepth.setEvent(EventType.SUB.getType());
        requestDepth.setParams(params);

        // kline
        Map<String, String> paramsKline = Maps.newHashMap();
        paramsKline.put("symbol", SYMBOL);
        paramsKline.put("klineType", CandlestickInterval.ONE_MINUTE.getIntervalId());

        ChannelRequest requestKline = new ChannelRequest();
        requestKline.setTopic(EventTopic.KLINE.getTopic());
        requestKline.setEvent(EventType.SUB.getType());
        requestKline.setParams(paramsKline);

        // ticker
        Map<String, String> paramsTicker = Maps.newHashMap();
        paramsTicker.put("symbol", SYMBOL);

        ChannelRequest requestTicker = new ChannelRequest();
        requestTicker.setTopic(EventTopic.REAL_TIMES.getTopic());
        requestTicker.setEvent(EventType.SUB.getType());
        requestTicker.setParams(paramsTicker);

        // index
        Map<String, String> paramsIndex = Maps.newHashMap();
        paramsIndex.put("symbol", SYMBOL_FUTURE);

        ChannelRequest requestIndex = new ChannelRequest();
        requestIndex.setTopic(EventTopic.INDEX.getTopic());
        requestIndex.setEvent(EventType.SUB.getType());
        requestIndex.setParams(paramsIndex);

        // trade
        Map<String, String> paramsTrade = Maps.newHashMap();
        paramsTrade.put("symbol", SYMBOL);

        ChannelRequest requestTrade = new ChannelRequest();
        requestTrade.setTopic(EventTopic.TRADE.getTopic());
        requestTrade.setEvent(EventType.SUB.getType());
        requestTrade.setParams(paramsTrade);

        // bookTicker
        Map<String, String> paramsBookticker = Maps.newHashMap();
        paramsBookticker.put("symbol", SYMBOL);

        ChannelRequest requestBookticker = new ChannelRequest();
        requestBookticker.setTopic(EventTopic.BOOK_TICKER.getTopic());
        requestBookticker.setEvent(EventType.SUB.getType());
        requestBookticker.setParams(paramsBookticker);

        List<ChannelRequest> channelRequestList = Arrays.asList(requestBookticker);
        client.onSubscribe(channelRequestList, new ApiWebSocketTestListener<>(apiConfig));

        // depth
        // client.onDepthEvent(SYMBOL, System.out::println);

        // kline
        // client.onCandlestickEvent("brokerbtcusdt123", CandlestickInterval.ONE_MINUTE, System.out::println);

        System.out.println("Listener..........");

        try {
            // waiting..........
            Thread.sleep(10000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
