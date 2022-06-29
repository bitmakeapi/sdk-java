package com.bitmake.open.api.service.websocket;

import com.bitmake.open.api.enums.EnumConnectionState;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ApiWebSocketWatcher {

    public static final long RECEIVE_LIMIT_TS = 1000 * 60 * 5;
    public static final int DELAY_ON_FAILURE = 15;

    private static final Map<Long, ApiWebSocketListener<?>> TIME_HELPER = new ConcurrentHashMap<>();

    static {
        long t = 30;
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(() -> {
            TIME_HELPER.entrySet().forEach(entry -> {
                ApiWebSocketListener<?> webSocketListener = entry.getValue();
                if (webSocketListener.getConnectionState() == EnumConnectionState.CONNECTED) {
                    // Check response
                    long ts = System.currentTimeMillis() - webSocketListener.getLastReceivedTime();
                    if (ts > RECEIVE_LIMIT_TS) {
                        log.warn("web socket sub no response from server");
                        webSocketListener.reConnect(DELAY_ON_FAILURE);
                    }
                    webSocketListener.sendPing();
                } else if (webSocketListener.getConnectionState() == EnumConnectionState.DELAY_CONNECT) {
                    webSocketListener.reConnect();
                } else if (webSocketListener.getConnectionState() == EnumConnectionState.CLOSED_ON_ERROR) {
                    webSocketListener.reConnect(DELAY_ON_FAILURE);
                }
            });
        }, t, t, TimeUnit.SECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(exec::shutdown));
    }

    private ApiWebSocketWatcher() {
    }


    public static void onConnectionCreated(ApiWebSocketListener<?> webSocketListener) {
        TIME_HELPER.put(webSocketListener.getWebsocketId(), webSocketListener);
    }

    public static void onClosedNormally(ApiWebSocketListener<?> webSocketListener) {
        TIME_HELPER.remove(webSocketListener);
    }
}
