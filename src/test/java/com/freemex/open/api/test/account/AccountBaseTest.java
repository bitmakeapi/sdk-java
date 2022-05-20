package com.freemex.open.api.test.account;

import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.test.BaseTests;

public class AccountBaseTest extends BaseTests {
    public FreemexApiConfig config() {
        FreemexApiConfig config = new FreemexApiConfig();
        config.setEndpoint("https://api.bytemars.co/");
        config.setApiKey("apiKey");
        config.setSecretKey("secretKey");

        return config;
    }
}
