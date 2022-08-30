package com.bitmake.open.api.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bitmake.open.api.config.BitmakeApiConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseTests {

    public BitmakeApiConfig config;

    public static final String SYMBOL = "BTC_USD";
    public static final String SYMBOL_FUTURE = "BTC-PERP";

    public void toResultString(String flag, Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            log.info("\n" + "=====>" + flag + ":\n" + mapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
