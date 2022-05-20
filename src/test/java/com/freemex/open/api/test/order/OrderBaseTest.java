package com.freemex.open.api.test.order;

import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.test.BaseTests;

public class OrderBaseTest extends BaseTests {
    public FreemexApiConfig config() {
        FreemexApiConfig config = new FreemexApiConfig();
        config.setEndpoint("https://api.bytemars.co/");
        config.setApiKey("apiKey");
        config.setSecretKey("secretKey");

        return config;
    }
}
