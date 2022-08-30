package com.bitmake.open.api.config;

import lombok.Data;

/**
 * APIConfiguration
 */
@Data
public class BitmakeApiConfig {

    /**
     * The user's api key provided by bitmake.
     */
    private String apiKey;
    /**
     * The user's secret key provided by bitmake. The secret key used to sign your request data.
     */
    private String secretKey;

    /**
     * Rest api endpoint url.
     */
    private String endpoint = "https://api.bitmake.com";

    private String quoteWsBaseUrl = "wss://ws.bitmake.com/t/v1/ws";

    private String userWsBaseUrl = "wss://ws.bitmake.com/t/v1/ws";

    private boolean socketReConnect = false;

    public void APIConfiguration(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    public void APIConfiguration(String apiKey, String secretKey, boolean socketReConnect) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.socketReConnect = socketReConnect;
    }

    public void APIConfiguration(String apiKey, String secretKey, String endpoint) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.endpoint = endpoint;
    }

    public void APIConfiguration(String apiKey, String secretKey, String endpoint, boolean socketReConnect) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.endpoint = endpoint;
        this.socketReConnect = socketReConnect;
    }
}
