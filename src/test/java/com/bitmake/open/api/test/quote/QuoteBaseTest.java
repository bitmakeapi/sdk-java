package com.bitmake.open.api.test.quote;

import com.bitmake.open.api.config.BitmakeApiConfig;
import com.bitmake.open.api.test.BaseTests;

public class QuoteBaseTest extends BaseTests {
    public BitmakeApiConfig config() {
        BitmakeApiConfig config = new BitmakeApiConfig();
        return config;
    }
}
