package com.freemex.open.api.test.websocket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.enums.EnumConnectionState;
import com.freemex.open.api.exception.FreemexApiException;
import com.freemex.open.api.service.ApiCallback;
import com.freemex.open.api.service.websocket.ApiWebSocketListener;
import com.freemex.open.api.service.websocket.ApiWebSocketWatcher;
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
public class ApiWebSocketTestListener<T> extends ApiWebSocketListener<T> {

    private static final String RESPONSE_CODE = "co";
    private static final String RESPONSE_MESSAGE = "m";

    public ApiWebSocketTestListener(FreemexApiConfig config)  {
        super(null, config, null);
    }

    @Override
    public void processMessage(String data) {
        log.debug("===on message===:{}", data);
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
            System.out.println(data);
            // callback.onResponse(getEventData(data));
        }
    }
}
