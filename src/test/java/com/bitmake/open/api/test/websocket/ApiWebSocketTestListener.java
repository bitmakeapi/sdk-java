package com.bitmake.open.api.test.websocket;

import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.service.websocket.ApiWebSocketListener;
import com.bitmake.open.api.service.websocket.ApiWebSocketWatcher;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiWebSocketTestListener<T> extends ApiWebSocketListener<T> {

    private static final String RESPONSE_CODE = "co";
    private static final String RESPONSE_MESSAGE = "m";

    public ApiWebSocketTestListener(BitmakeApiConfig config)  {
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
