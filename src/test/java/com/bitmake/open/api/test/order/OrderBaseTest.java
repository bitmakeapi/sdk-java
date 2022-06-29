package com.bitmake.open.api.test.order;

import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.test.BaseTests;

public class OrderBaseTest extends BaseTests {
    public BitmakeApiConfig config() {
        BitmakeApiConfig config = new BitmakeApiConfig();
        config.setApiKey("apiKey");
        config.setSecretKey("secretKey");
        return config;
    }
}
