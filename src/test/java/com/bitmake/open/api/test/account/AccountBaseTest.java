package com.bitmake.open.api.test.account;

import com.bitmake.open.api.test.BaseTests;
import com.bitmake.open.api.config.BitmakeApiConfig;

public class AccountBaseTest extends BaseTests {
    public BitmakeApiConfig config() {
        BitmakeApiConfig config = new BitmakeApiConfig();
        config.setApiKey("apiKey");
        config.setSecretKey("secretKey");
        // config.setDebugModel(true);
        return config;
    }
}
