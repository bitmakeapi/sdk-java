package com.freemex.open.api.service.websocket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.enums.EnumConnectionState;
import com.freemex.open.api.exception.FreemexApiException;
import com.freemex.open.api.service.ApiCallback;
import com.freemex.open.api.utils.IdGenerator;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

@Slf4j
public class ApiWebSocketListener<T> extends WebSocketListener {

    private final ApiCallback<T> callback;

    private Class<T> eventClass;

    private TypeReference<T> eventTypeReference;

    private long lastReceivedTime;

    private EnumConnectionState connectionState;

    private WebSocket webSocket;

    private final long websocketId;

    private long delayInSecond;

    private Request request;
    private OkHttpClient client;
    private FreemexApiConfig config;

    private static final String RESPONSE_CODE = "co";
    private static final String RESPONSE_MESSAGE = "m";

    public ApiWebSocketListener(ApiCallback<T> callback, FreemexApiConfig config, Class<T> eventClass) {
        this.callback = callback;
        this.eventClass = eventClass;
        this.config = config;
        websocketId = IdGenerator.getNextId();
    }

    public ApiWebSocketListener(ApiCallback<T> callback, FreemexApiConfig config, TypeReference<T> eventTypeReference) {
        this.callback = callback;
        this.eventTypeReference = eventTypeReference;
        this.config = config;
        websocketId = IdGenerator.getNextId();
    }

    public EnumConnectionState getConnectionState() {
        return connectionState;
    }

    public Long getLastReceivedTime() {
        return lastReceivedTime;
    }

    public long getWebsocketId() {
        return websocketId;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public void reConnect(int delayInSecond) {
        log.warn("web socket reconnecting after " + delayInSecond + " seconds later");
        if (webSocket != null) {
            webSocket.cancel();
            webSocket = null;
        }
        this.delayInSecond = delayInSecond;
        connectionState = EnumConnectionState.DELAY_CONNECT;
    }

    public void reConnect() {
        if (delayInSecond != 0) {
            delayInSecond--;
        } else {
            connect();
        }
    }

    public void send(String str) {
        boolean result = false;
        log.info("[Connection Send]{}", str);
        if (webSocket != null) {
            result = webSocket.send(str);
        }
        if (!result) {
            log.error("[Connection Send] Failed to send message");
            if (webSocket != null) {
                this.webSocket.cancel();
                connectionState = EnumConnectionState.CLOSED_ON_ERROR;
                log.error("[Connection error] Connection is closing due to error");
            }
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        this.webSocket = webSocket;
        log.info("web socket connected to server");
        connectionState = EnumConnectionState.CONNECTED;
        try {
            if (config.isSocketReConnect()) {
                ApiWebSocketWatcher.onConnectionCreated(this);
            }
            lastReceivedTime = System.currentTimeMillis();
            log.info(response.body().string());
        } catch (IOException e) {
            log.error("web socket on open failed, error :{}", e.getMessage());
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        lastReceivedTime = System.currentTimeMillis();

        String data;
        try {
            bytes.asByteBuffer();
            data = decompress(bytes.toByteArray());
        } catch (Exception e) {
            log.error("[Connection On Message] Receive message error: " + e.getMessage());
            closeOnError();
            return;
        }
        processMessage(data);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        lastReceivedTime = System.currentTimeMillis();
        processMessage(text);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        log.error("[Connection error]:{}", t.getMessage());
        closeOnError();
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        log.info("web socket closed:code->{},reason->{}", code, reason);
        if (connectionState == EnumConnectionState.CONNECTED) {
            connectionState = EnumConnectionState.IDLE;
        }
    }

    private T getEventData(String data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            T event;
            if (eventClass == null) {
                event = mapper.readValue(data, eventTypeReference);
            } else {
                event = mapper.readValue(data, eventClass);
            }
            return event;
        } catch (IOException e) {
            throw new FreemexApiException(e);
        }
    }

    private void processMessage(String data) {
        log.debug("on message:{}", data);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(data).getAsJsonObject();
        // sub response
        if (jsonObject.has(RESPONSE_CODE) && !"0".equals(jsonObject.get(RESPONSE_CODE).getAsString())) {
            String code = jsonObject.get(RESPONSE_CODE).getAsString();
            String msg = jsonObject.get(RESPONSE_MESSAGE).getAsString();
            log.error("web socket get error from server:code->{}, msg->{} ", code, msg);
            closeOnError();
            webSocket = null;
            ApiWebSocketWatcher.onClosedNormally(this);
        } else if (jsonObject.has("ping")) {
            // ping pone
            sendPone(jsonObject, webSocket);
        } else if (!jsonObject.has(RESPONSE_CODE)) {
            // received data
            callback.onResponse(getEventData(data));
        }
    }

    private void sendPone(JsonObject jsonObject, WebSocket webSocket) {
        long ts = jsonObject.get("ping").getAsLong();
        webSocket.send(String.format("{\"pong\":%d}", ts));
    }

    private void closeOnError() {
        if (webSocket != null) {
            this.webSocket.cancel();
            connectionState = EnumConnectionState.CLOSED_ON_ERROR;
            log.error("Connection is closing due to error");
        }
    }

    void connect() {
        if (connectionState == EnumConnectionState.CONNECTED) {
            log.info("web socket connection already connected");
            return;
        }
        if (client != null && request != null) {
            webSocket = client.newWebSocket(request, this);
        } else {
            log.error("connect error: client or request is null");
        }
    }

    /**
     * decompress gzip message
     *
     * @param depressData
     * @return
     */
    private String decompress(byte[] depressData) {
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(depressData);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            if (isCompressed(depressData)) {
                // gzip
                GZIPInputStream gis = new GZIPInputStream(is);
                int count;
                byte data[] = new byte[1024];
                while ((count = gis.read(data, 0, 1024)) != -1) {
                    os.write(data, 0, count);
                }
                gis.close();
                depressData = os.toByteArray();
            } else {

            }
            os.flush();
            os.close();
            is.close();
            return new String(depressData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.warn("decompress data error：", e);
        }
        return null;
    }

    private boolean isCompressed(byte[] bytes) {
        if ((bytes == null) || (bytes.length < 2)) {
            return false;
        } else {
            return ((bytes[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (bytes[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8)));
        }
    }
}
