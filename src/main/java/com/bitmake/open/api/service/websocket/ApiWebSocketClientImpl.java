package com.bitmake.open.api.service.websocket;

import com.bitmake.open.api.domain.event.*;
import com.bitmake.open.api.utils.SignUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.constant.APIConstants;
import com.bitmake.open.api.domain.CandlestickInterval;
import com.bitmake.open.api.exception.BitmakeApiException;
import com.bitmake.open.api.service.ApiCallback;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.io.Closeable;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Map;

@Slf4j
public class ApiWebSocketClientImpl implements ApiWebSocketClient, Cloneable {

    private final OkHttpClient client;
    private final BitmakeApiConfig config;

    public ApiWebSocketClientImpl(OkHttpClient client, BitmakeApiConfig config) {
        this.client = client;
        this.config = config;
    }

    @Override
    public Closeable onDepthEvent(String symbol, ApiCallback<DepthEvent> callback) {

        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol);
        params.put("dumpScale", "0");
        params.put("depthLevel", "0");

        ChannelRequest request = new ChannelRequest();
        request.setTopic(EventTopic.DIFF_MERGED_DEPTH.getTopic());
        request.setEvent(EventType.SUB.getType());
        request.setParams(params);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String channel = mapper.writeValueAsString(request);
            return createNewWebSocket(channel, new ApiWebSocketListener<>(callback, config, DepthEvent.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Closeable onCandlestickEvent(String symbols, CandlestickInterval interval, ApiCallback<CandlestickEvent> callback) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbols);
        params.put("klineType", interval.getIntervalId());

        ChannelRequest request = new ChannelRequest();
        request.setTopic(EventTopic.KLINE.getTopic());
        request.setEvent(EventType.SUB.getType());
        request.setParams(params);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String channel = mapper.writeValueAsString(request);
            return createNewWebSocket(channel, new ApiWebSocketListener<>(callback, config, CandlestickEvent.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Closeable onTicker24HourEvent(String symbols, ApiCallback<TickerEvent> callback) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbols);

        ChannelRequest request = new ChannelRequest();
        request.setTopic(EventTopic.REAL_TIMES.getTopic());
        request.setEvent(EventType.SUB.getType());
        request.setParams(params);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String channel = mapper.writeValueAsString(request);
            return createNewWebSocket(channel, new ApiWebSocketListener<>(callback, config, TickerEvent.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Closeable onIndexEvent(String symbols, ApiCallback<IndexEvent> callback) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbols);

        ChannelRequest request = new ChannelRequest();
        request.setTopic(EventTopic.INDEX.getTopic());
        request.setEvent(EventType.SUB.getType());
        request.setParams(params);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String channel = mapper.writeValueAsString(request);
            return createNewWebSocket(channel, new ApiWebSocketListener<>(callback, config, IndexEvent.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Closeable onTradeEvent(String symbols, ApiCallback<TradeEvent> callback) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbols);

        ChannelRequest request = new ChannelRequest();
        request.setTopic(EventTopic.TRADE.getTopic());
        request.setEvent(EventType.SUB.getType());
        request.setParams(params);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String channel = mapper.writeValueAsString(request);
            return createNewWebSocket(channel, new ApiWebSocketListener<>(callback, config, TradeEvent.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Closeable onBookTickerEvent(String symbols, ApiCallback<BookTickerEvent> callback) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbols);

        ChannelRequest request = new ChannelRequest();
        request.setTopic(EventTopic.BOOK_TICKER.getTopic());
        request.setEvent(EventType.SUB.getType());
        request.setParams(params);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String channel = mapper.writeValueAsString(request);
            return createNewWebSocket(channel, new ApiWebSocketListener<>(callback, config, BookTickerEvent.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * subscribe channels
     *
     * @param requests
     * @return
     */
    @Override
    public Closeable onSubscribe(List<ChannelRequest> requests, ApiWebSocketListener<?> listener) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String streamingUrl = config.getQuoteWsBaseUrl();

        Request.Builder requestBuilder = new Request.Builder().url(streamingUrl);
        if (!Strings.isNullOrEmpty(config.getApiKey())) {
            requestBuilder.addHeader(APIConstants.FM_API_KEY, config.getApiKey())
                    .addHeader(APIConstants.FM_API_SIGN, this.sign(timestamp))
                    .addHeader(APIConstants.FM_API_TIMESTAMP, timestamp)
                    .addHeader("vcode", "aab");
        }

        Request conRequest = requestBuilder.build();
        listener.setRequest(conRequest);

        final WebSocket webSocket = client.newWebSocket(conRequest, listener);
        ObjectMapper mapper = new ObjectMapper();
        requests.forEach(channelRequest -> {
            try {
                String channel = mapper.writeValueAsString(channelRequest);
                webSocket.send(channel);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        return createCloseable(listener, webSocket);
    }

    /**
     * @deprecated This method is no longer functional.
     * Please use the returned {@link Closeable} from any of the other methods to close the web socket.
     */
    @Override
    public void close() {
    }

    private Closeable createNewWebSocket(String channel, ApiWebSocketListener<?> listener) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String streamingUrl = config.getQuoteWsBaseUrl();
        Request.Builder requestBuilder = new Request.Builder().url(streamingUrl);
        if (!Strings.isNullOrEmpty(config.getApiKey())) {
            requestBuilder.addHeader(APIConstants.FM_API_KEY, config.getApiKey())
                    .addHeader(APIConstants.FM_API_SIGN, this.sign(timestamp))
                    .addHeader(APIConstants.FM_API_TIMESTAMP, timestamp)
                    .addHeader("vcode", "aab");
        }
        Request request = requestBuilder.build();
        listener.setRequest(request);
        final WebSocket webSocket = client.newWebSocket(request, listener);
        webSocket.send(channel);
        return createCloseable(listener, webSocket);
    }

    private String sign(final String timestamp) {
        final String sign;
        try {
            sign = SignUtils.sign(config.getApiKey(), timestamp, "GET", "/t/v1/ws", "", "", config.getSecretKey());
        } catch (final IOException e) {
            throw new BitmakeApiException("Request get body io exception.", e);
        } catch (final CloneNotSupportedException e) {
            throw new BitmakeApiException("Hmac SHA256 Base64 Signature clone not supported exception.", e);
        } catch (final InvalidKeyException e) {
            throw new BitmakeApiException("Hmac SHA256 Base64 Signature invalid key exception.", e);
        }
        return sign;
    }

    public Closeable createCloseable(WebSocketListener listener, final WebSocket webSocket) {
        try {
            log.info("close-========");
            return () -> {
                final int code = 1000;
                listener.onClosing(webSocket, code, null);
                webSocket.close(code, null);
                listener.onClosed(webSocket, code, null);
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
