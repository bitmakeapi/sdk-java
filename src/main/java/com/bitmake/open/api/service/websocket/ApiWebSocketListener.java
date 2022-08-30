package com.bitmake.open.api.service.websocket;

import com.bitmake.open.api.enums.EnumConnectionState;
import com.bitmake.open.api.utils.IdGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.exception.BitmakeApiException;
import com.bitmake.open.api.service.ApiCallback;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

@Slf4j
public class ApiWebSocketListener<T> extends WebSocketListener {

    private final ApiCallback<T> callback;

    private final Class<T> eventClass;

    private long lastReceivedTime;

    private EnumConnectionState connectionState;

    protected WebSocket webSocket;

    private final long websocketId;

    private long delayInSecond;

    private Request request;
    private OkHttpClient client;
    private final BitmakeApiConfig config;

    private static final String RESPONSE_CODE = "co";
    private static final String RESPONSE_MESSAGE = "m";

    public ApiWebSocketListener(ApiCallback<T> callback, BitmakeApiConfig config, Class<T> eventClass) {
        this.callback = callback;
        this.eventClass = eventClass;
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

    public void setRequest(Request conRequest) {
        request = conRequest;
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
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
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
    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
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
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        lastReceivedTime = System.currentTimeMillis();
        processMessage(text);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, Throwable t, Response response) {
        log.error("[Connection error]:{}", t.getMessage());
        closeOnError();
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosed(webSocket, code, reason);
        log.info("web socket closed:code->{},reason->{}", code, reason);
        if (connectionState == EnumConnectionState.CONNECTED) {
            connectionState = EnumConnectionState.IDLE;
        }
    }

    private T getEventData(String data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(data, eventClass);
        } catch (IOException e) {
            throw new BitmakeApiException(e);
        }
    }

    public void processMessage(String data) {
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
            sendPone(jsonObject);
        } else if (!jsonObject.has(RESPONSE_CODE)) {
            // received data
            callback.onResponse(getEventData(data));
        }
    }

    public void sendPone(JsonObject jsonObject) {
        long ts = jsonObject.get("ping").getAsLong();
        webSocket.send(String.format("{\"pong\":%d}", ts));
    }

    public void sendPing() {
        System.out.println("send ping");
        webSocket.send(String.format("{\"ping\":%d}", System.currentTimeMillis()));
    }

    public void closeOnError() {
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
    public String decompress(byte[] depressData) {
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

    public boolean isCompressed(byte[] bytes) {
        if ((bytes == null) || (bytes.length < 2)) {
            return false;
        } else {
            return ((bytes[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (bytes[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8)));
        }
    }
}
