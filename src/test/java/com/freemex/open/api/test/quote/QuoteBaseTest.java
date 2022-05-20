package com.freemex.open.api.test.quote;

import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.test.BaseTests;

public class QuoteBaseTest extends BaseTests {
    public FreemexApiConfig config() {
        FreemexApiConfig config = new FreemexApiConfig();
        config.setEndpoint("https://api.bytemars.co");
        return config;
    }
}
