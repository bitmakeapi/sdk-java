package com.freemex.open.api.config;

import lombok.Data;

/**
 * APIConfiguration
 */
@Data
public class FreemexApiConfig {

    /**
     * The user's api key provided by freemex.
     */
    private String apiKey;
    /**
     * The user's secret key provided by freemex. The secret key used to sign your request data.
     */
    private String secretKey;

    /**
     * Rest api endpoint url.
     */
    private String endpoint;

    private String quoteWsBaseUrl;

    private String userWsBaseUrl;

    private boolean socketReConnect = false;

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
