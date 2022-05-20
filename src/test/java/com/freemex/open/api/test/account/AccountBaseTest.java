package com.freemex.open.api.test.account;

import com.freemex.open.api.config.FreemexApiConfig;
import com.freemex.open.api.test.BaseTests;

public class AccountBaseTest extends BaseTests {
    public FreemexApiConfig config() {
        FreemexApiConfig config = new FreemexApiConfig();
        config.setEndpoint("https://api.bytemars.co/");
        config.setApiKey("aabIN8901nT1tpF1N7FTNftdDIxXPhSbQcJtqkjwDcNUuZIVUYhLCxGxtdD0WUoB");
        config.setSecretKey("BIhVyOm072LC6pWTePM4SWAvC0EN0wMMYE7KM6TYdAqwBpdMHmOcfZQKxzPmqOOq");

        return config;
    }
}
